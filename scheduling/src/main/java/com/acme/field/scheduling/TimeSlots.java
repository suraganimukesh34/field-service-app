package com.acme.field.scheduling;

import java.util.concurrent.TimeUnit;

public final class TimeSlots {

    public static final int DEFAULT_SLOT_MINUTES = 30;
    public static final long DAY_MILLIS = TimeUnit.DAYS.toMillis(1);

    private TimeSlots() {
    }

    public static boolean overlaps(Appointment a, Appointment b) {
        return a.getStartMillis() < b.getEndMillis() && b.getStartMillis() < a.getEndMillis();
    }

    public static long roundToSlot(long millis) {
        long slot = TimeUnit.MINUTES.toMillis(DEFAULT_SLOT_MINUTES);
        return (millis / slot) * slot;
    }

    public static boolean isSameDay(long first, long second) {
        return first / DAY_MILLIS == second / DAY_MILLIS;
    }
}
