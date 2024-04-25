package com.cedricverlinden.banking.constants;

import java.time.LocalTime;

public enum TimeOfDay {
    MORNING,
    AFTERNOON,
    EVENING,
    NIGHT;

    public static TimeOfDay getCurrentTimeOfDay() {
        String currentTime = LocalTime.now().toString();
        String[] time = currentTime.split(":");

        int hour = Integer.parseInt(time[0]);
        
        if (hour >= 6 && hour <= 11) {
            return MORNING;
        } else if (hour >= 12 && hour <= 17) {
            return AFTERNOON;
        } else if (hour >= 18 && hour <= 20) {
            return EVENING;
        }
        
        return NIGHT;
    }
}
