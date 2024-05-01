package com.hossana.expensemanager.utils;

import android.content.res.Configuration;

public class DarkModeChecker {
    public static boolean isDarkModeEnabled(Configuration configuration) {
        int currentMode = configuration.uiMode & Configuration.UI_MODE_NIGHT_MASK;
        return currentMode == Configuration.UI_MODE_NIGHT_YES;
    }
}
