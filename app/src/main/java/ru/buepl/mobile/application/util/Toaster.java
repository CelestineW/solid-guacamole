package ru.buepl.mobile.application.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

public final class Toaster {
    private Toaster() {
    }

    public static void toastException(@NonNull Context context,
                                      @Nullable Exception exception) {
        String message = (exception == null) ? "Unknown Error" : exception.getMessage();
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
