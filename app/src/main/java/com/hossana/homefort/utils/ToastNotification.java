package com.hossana.homefort.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.res.ResourcesCompat;

import com.hossana.homefort.R;

public class ToastNotification {
    public static void toastNotification(Context context, String message, String type) {
        boolean successToast = type.equalsIgnoreCase("success");

        LayoutInflater inflater = LayoutInflater.from(context);
        int toast_type = successToast ? R.layout.toast_success : R.layout.toast_error;
        View layout = inflater.inflate(toast_type, (ViewGroup) null, false);

        TextView text = layout.findViewById(successToast ? R.id.timex_toast_text : R.id.error_timex_toast_text);
        text.setText(message);
        Typeface font = ResourcesCompat.getFont(context, R.font.redressed);
        text.setTypeface(font);

        Toast toast = new Toast(context);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);

        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 40);

        toast.show();
    }
}
