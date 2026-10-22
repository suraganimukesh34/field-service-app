package com.acme.field.scheduling;

import java.util.Objects;

public class Appointment {

    private final String id;
    private final String customerId;
    private final long startMillis;
    private final int durationMinutes;
    private final String technician;
    private final String notes;
    private AppointmentStatus status;

    private Appointment(Builder builder) {
        this.id = builder.id;
        this.customerId = builder.customerId;
        this.startMillis = builder.startMillis;
        this.durationMinutes = builder.durationMinutes;
        this.technician = builder.technician;
        this.notes = builder.notes;
        this.status = AppointmentStatus.REQUESTED;
    }

    public String getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public long getStartMillis() {
        return startMillis;
    }

    public long getEndMillis() {
        return startMillis + durationMinutes * 60_000L;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public String getTechnician() {
        return technician;
    }

    public String getNotes() {
        return notes;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Appointment)) {
            return false;
        }
        Appointment other = (Appointment) o;
        return Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public static class Builder {
        private final String id;
        private final String customerId;
        private long startMillis;
        private int durationMinutes = 60;
        private String technician;
        private String notes;

        public Builder(String id, String customerId) {
            this.id = id;
            this.customerId = customerId;
        }

        public Builder start(long startMillis) {
            this.startMillis = startMillis;
            return this;
        }

        public Builder duration(int minutes) {
            this.durationMinutes = minutes;
            return this;
        }

        public Builder technician(String technician) {
            this.technician = technician;
            return this;
        }

        public Builder notes(String notes) {
            this.notes = notes;
            return this;
        }

        public Appointment build() {
            return new Appointment(this);
        }
    }
}
