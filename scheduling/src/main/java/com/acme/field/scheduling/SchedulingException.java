package com.acme.field.scheduling;

public class SchedulingException extends Exception {

    private final String appointmentId;

    public SchedulingException(String message, String appointmentId) {
        super(message);
        this.appointmentId = appointmentId;
    }

    public String getAppointmentId() {
        return appointmentId;
    }
}
