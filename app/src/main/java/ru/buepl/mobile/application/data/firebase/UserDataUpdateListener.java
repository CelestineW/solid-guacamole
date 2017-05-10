package ru.buepl.mobile.application.data.firebase;

import android.support.annotation.NonNull;

import ru.buepl.mobile.application.data.AccountInfo;
import ru.buepl.mobile.application.data.Application;

/**
 * A listener for updates to a user's data.
 */
public interface UserDataUpdateListener {
    /**
     * A callback invoked with updates to a user's data.
     *
     * @param accountInfo the user's current {@link AccountInfo account information}
     * @param application the user's current {@link Application application}
     */
    void onUpdate(@NonNull AccountInfo accountInfo, @NonNull Application application);
}
