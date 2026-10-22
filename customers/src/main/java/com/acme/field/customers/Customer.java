package com.acme.field.customers;

import java.util.Objects;

public class Customer {

    private final String id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private boolean active;

    public Customer(String id, String name, String email) {
        this(id, name, email, null, null, true);
    }

    public Customer(String id, String name, String email, String phone, String address, boolean active) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.active = active;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Customer other = (Customer) o;
        return active == other.active
                && Objects.equals(id, other.id)
                && Objects.equals(name, other.name)
                && Objects.equals(email, other.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, active);
    }

    @Override
    public String toString() {
        return "Customer{id='" + id + "', name='" + name + "', email='" + email + "', active=" + active + "}";
    }
}
