package com.hossana.homefort.utils;

import java.util.Calendar;

public class GreetingHandler {
    public static String getGreeting() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        if (hour >= 6 && hour < 12) {
            return "Good Morning";
        } else if (hour >= 12 && hour < 16) {
            return "Good Afternoon";
        } else {
            return "Good Evening";
        }
    }
}
