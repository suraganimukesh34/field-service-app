package com.acme.field.scheduling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AppointmentService {

    private final List<Appointment> appointments = new ArrayList<>();
    private final List<AppointmentListener> listeners = new ArrayList<>();

    public void addListener(AppointmentListener listener) {
        listeners.add(listener);
    }

    public void schedule(Appointment appointment) throws SchedulingException {
        for (Appointment existing : appointments) {
            if (existing.getStatus().isFinished()) {
                continue;
            }
            boolean sameTechnician = existing.getTechnician() != null
                    && existing.getTechnician().equals(appointment.getTechnician());
            if (sameTechnician && TimeSlots.overlaps(existing, appointment)) {
                throw new SchedulingException(
                        "Technician " + appointment.getTechnician() + " is already booked", appointment.getId());
            }
        }
        appointments.add(appointment);
    }

    public void changeStatus(Appointment appointment, AppointmentStatus next) throws SchedulingException {
        AppointmentStatus previous = appointment.getStatus();
        if (!previous.canTransitionTo(next)) {
            throw new SchedulingException(
                    "Cannot go from " + previous.getLabel() + " to " + next.getLabel(), appointment.getId());
        }
        appointment.setStatus(next);
        for (AppointmentListener listener : listeners) {
            listener.onStatusChanged(appointment, previous);
        }
    }

    public List<Appointment> forDay(long dayMillis) {
        List<Appointment> result = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (TimeSlots.isSameDay(appointment.getStartMillis(), dayMillis)) {
                result.add(appointment);
            }
        }
        Collections.sort(result, new Comparator<Appointment>() {
            @Override
            public int compare(Appointment a, Appointment b) {
                return Long.compare(a.getStartMillis(), b.getStartMillis());
            }
        });
        return result;
    }

    public int openCount() {
        int count = 0;
        for (Appointment appointment : appointments) {
            if (!appointment.getStatus().isFinished()) {
                count++;
            }
        }
        return count;
    }
}
