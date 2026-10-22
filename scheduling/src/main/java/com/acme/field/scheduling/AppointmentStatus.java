package com.acme.field.scheduling;

public enum AppointmentStatus {
    REQUESTED("Requested"),
    CONFIRMED("Confirmed"),
    IN_PROGRESS("In progress"),
    COMPLETED("Completed"),
    CANCELLED("Cancelled");

    private final String label;

    AppointmentStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public boolean canTransitionTo(AppointmentStatus next) {
        switch (this) {
            case REQUESTED:
                return next == CONFIRMED || next == CANCELLED;
            case CONFIRMED:
                return next == IN_PROGRESS || next == CANCELLED;
            case IN_PROGRESS:
                return next == COMPLETED;
            default:
                return false;
        }
    }

    public boolean isFinished() {
        return this == COMPLETED || this == CANCELLED;
    }
}
