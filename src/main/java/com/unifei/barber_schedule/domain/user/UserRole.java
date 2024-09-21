package com.unifei.barber_schedule.domain.user;

public enum UserRole {
    CLIENT("client"),
    ADMIN("admin"),
    BARBER("barber");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public String toUpperCase() {
        return role.toUpperCase();
    }
}