package ru.buepl.mobile.application;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import ru.buepl.mobile.application.data.Application;
import ru.buepl.mobile.application.data.firebase.FirebaseHelper;
import ru.buepl.mobile.application.util.Toaster;


abstract class LoggedInActivity extends AppCompatActivity {
    private volatile boolean loggingOut = false;

    @Override
    protected void onStart() {
        super.onStart();
        loggingOut = false;
    }

    /**
     * Collects application data to be saved.
     * <p>
     * A return value of {@code null} indicates that there is no data
     * to save.
     *
     * @return the {@link Application application data} to save
     */
    @Nullable
    protected abstract Application collectApplicationDataToSave();

    /**
     * Saves {@link Application application data} for this activity.
     * <p>
     * Note: If {@link #collectApplicationDataToSave()} returns {@code null},
     * this method returns {@code false}, and the listener is not invoked.
     *
     * @param listener a callback to invoke when the operation is complete
     * @return true if there is data to save (and the callback will be invoked);
     * false otherwise.
     */
    protected final boolean saveApplicationData(@Nullable final OnCompleteListener<Void> listener) {
        Application application = collectApplicationDataToSave();
        if (application != null) {
            FirebaseHelper.getInstance().saveApplicationForCurrentUser(application, new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (!task.isSuccessful()) {
                        Toaster.toastException(LoggedInActivity.this, task.getException());
                    }

                    if (listener != null) {
                        listener.onComplete(task);
                    }
                }
            });
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (!loggingOut) {
            saveApplicationData(null);
        }
    }

    @Override
    public final boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout_menu, menu);

        menu.findItem(R.id.menu_item_logout)
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        OnCompleteListener<Void> listener = new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    doLogout();
                                }
                            }
                        };

                        String message = getResources().getString(R.string.logging_out);
                        Toast.makeText(LoggedInActivity.this, message, Toast.LENGTH_SHORT).show();
                        if (!saveApplicationData(listener)) {
                            doLogout();
                        }
                        return true;
                    }
                });

        return true;
    }

    /**
     * Logs out and finishes this activity.
     */
    private void doLogout() {
        loggingOut = true;
        FirebaseHelper.getInstance().logout();

        final Intent intent = new Intent(LoggedInActivity.this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
                finish();
            }
        });
    }
}
