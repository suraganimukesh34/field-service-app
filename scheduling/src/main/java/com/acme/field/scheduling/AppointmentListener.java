package com.acme.field.scheduling;

public interface AppointmentListener {

    void onStatusChanged(Appointment appointment, AppointmentStatus previous);
}
