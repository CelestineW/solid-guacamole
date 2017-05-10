package ru.buepl.mobile.application.data.firebase;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import ru.buepl.mobile.application.data.AccountInfo;
import ru.buepl.mobile.application.data.Application;

/**
 * A (singleton) helper for Firebase transactions.
 *
 * @see #getInstance()
 */
public enum FirebaseHelper {
    INSTANCE;

    private static final String USERS_CHILD = "users";
    private static final String ACCOUNT_INFO_CHILD = "currentAccountInfo";
    private static final String APPLICATION_CHILD = "application";

    /**
     * Returns the {@code FirebaseHelper} instance.
     *
     * @return the FirebaseHelper instance.
     */
    public static FirebaseHelper getInstance() {
        return INSTANCE;
    }

    private final FirebaseAuth auth = FirebaseAuth.getInstance();
    private final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();

    private volatile Application currentApplication = null;
    private volatile AccountInfo currentAccountInfo;

    /**
     * Returns {@code true} if a user is logged in; {@code false} otherwise.
     *
     * @return true if a user is logged in; false otherwise
     */
    public boolean loggedIn() {
        return auth.getCurrentUser() != null;
    }

    /**
     * Logs a user into Firebase.
     *
     * @param email          the user's email address
     * @param password       the user's password
     * @param onLoginSuccess a callback to be invoked if the login is successful;
     *                       it is invoked after fetching user data
     * @param onLoginFailure a callback to be invoked if the login fails
     */
    public void login(@NonNull String email,
                      @NonNull String password,
                      @Nullable final UserDataUpdateListener onLoginSuccess,
                      @Nullable final OnCompleteListener<AuthResult> onLoginFailure) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            fetchRemoteDataForUser(onLoginSuccess);
                        } else {
                            if (onLoginFailure != null) {
                                onLoginFailure.onComplete(task);
                            }
                        }
                    }
                });
    }


    /**
     * Creates a new Firebase account.
     *
     * @param email       the user's email address
     * @param password    the user's password
     * @param accountInfo the user's {@link AccountInfo account information}
     * @param onSuccess   a callback to be invoked if the account creation is successful;
     *                    it is invoked after fetching user data
     * @param onFailure   a callback to be invoked if the account creation fails
     */
    public void createAccount(@NonNull String email,
                              @NonNull String password,
                              @NonNull final AccountInfo accountInfo,
                              @Nullable final UserDataUpdateListener onSuccess,
                              @Nullable final OnCompleteListener<AuthResult> onFailure) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            saveAccountInfoForCurrentUser(accountInfo);
                            fetchRemoteDataForUser(onSuccess);
                        } else {
                            if (onFailure != null) {
                                onFailure.onComplete(task);
                            }
                        }
                    }
                });
    }

    /**
     * Logs the current user out of Firebase.
     */
    public void logout() {
        auth.signOut();
    }

    /**
     * Updates local copies of data for a user.
     * <p>
     * Only needs to be called manually when the app starts - it is
     * called automatically on login.
     *
     * @param onComplete a callback to be invoked after the data is fetched
     * @throws IllegalStateException if no user is logged in
     */
    public void fetchRemoteDataForUser(@Nullable final UserDataUpdateListener onComplete)
            throws IllegalStateException {
        dbReferenceForCurrentUser()
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Read account info
                        AccountInfo accountInfo = dataSnapshot
                                .child(ACCOUNT_INFO_CHILD)
                                .getValue(AccountInfo.class);
                        if (accountInfo == null) {
                            Log.wtf("null account info", "should not happen!!"); // TODO: 5/9/17 crash?
                            accountInfo = AccountInfo.builder().build();
                        }
                        currentAccountInfo = accountInfo;

                        // Read application
                        Application application = dataSnapshot
                                .child(APPLICATION_CHILD)
                                .getValue(Application.class);
                        if (application == null) {
                            application = new Application();
                        }
                        currentApplication = application;

                        if (onComplete != null) {
                            onComplete.onUpdate(accountInfo, application);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Will not cancel
                    }
                });
    }

    /**
     * Returns the {@link Application application} for the current user.
     *
     * @return the application for the current user
     * @throws IllegalStateException if no user is logged in
     */
    public Application getApplication() throws IllegalStateException {
        getLoggedInUser(); // Assert that logged in
        return currentApplication;
    }

    /**
     * Returns the {@link AccountInfo account information} for the current user.
     *
     * @return the account information for the current user
     * @throws IllegalStateException if no user is logged in
     */
    public AccountInfo getAccountInfo() throws IllegalStateException {
        getLoggedInUser(); // Assert that logged in
        return currentAccountInfo;
    }

    /**
     * Saves the specified {@link AccountInfo account information} for the current user.
     *
     * @param accountInfo the account information to save
     * @throws IllegalStateException if no user is logged in
     */
    public void saveAccountInfoForCurrentUser(@NonNull AccountInfo accountInfo)
            throws IllegalStateException {
        saveAccountInfoForCurrentUser(accountInfo, null);
    }

    /**
     * Saves the specified {@link AccountInfo account information} for the current user.
     *
     * @param accountInfo the account information to save
     * @param listener    a callback to invoke once the action is complete
     * @throws IllegalStateException if no user is logged in
     */
    public void saveAccountInfoForCurrentUser(@NonNull AccountInfo accountInfo,
                                              @Nullable final OnCompleteListener<Void> listener)
            throws IllegalStateException {
        dbReferenceForCurrentUser()
                .child(ACCOUNT_INFO_CHILD).setValue(accountInfo)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (listener != null) {
                            listener.onComplete(task);
                        }
                    }
                });
    }

    /**
     * Saves the specified {@link Application application} for the current user.
     *
     * @param application the application to save
     * @throws IllegalStateException if no user is logged in
     */
    public void saveApplicationForCurrentUser(@NonNull Application application)
            throws IllegalStateException {
        saveApplicationForCurrentUser(application, null);
    }

    /**
     * Saves the specified {@link Application application} for the current user.
     *
     * @param application the application to save
     * @param listener    a callback to invoke once the action is complete
     * @throws IllegalStateException if no user is logged in
     */
    public void saveApplicationForCurrentUser(@NonNull Application application,
                                              @Nullable final OnCompleteListener<Void> listener)
            throws IllegalStateException {
        currentApplication = application;

        dbReferenceForCurrentUser()
                .child(APPLICATION_CHILD)
                .setValue(application)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (listener != null) {
                            listener.onComplete(task);
                        }
                    }
                });
    }

    /**
     * Returns the currently logged-in user.
     *
     * @return the currently logged-in user
     * @throws IllegalStateException if no user is logged in
     */
    private FirebaseUser getLoggedInUser() throws IllegalStateException {
        FirebaseUser user = auth.getCurrentUser();
        if (user == null) {
            throw new IllegalStateException("No user logged in");
        }
        return user;
    }

    /**
     * Returns a {@link DatabaseReference} for the currently logged-in user.
     *
     * @return a DatabaseReference for the currently logged-in user
     * @throws IllegalStateException if no user is logged in
     */
    private DatabaseReference dbReferenceForCurrentUser() throws IllegalStateException {
        return dbRef.child(USERS_CHILD).child(getLoggedInUser().getUid());
    }
}
