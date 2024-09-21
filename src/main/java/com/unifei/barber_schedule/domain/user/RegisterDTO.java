package com.unifei.barber_schedule.domain.user;

public record RegisterDTO(String name, String phone, String email, String password, UserRole role) {
}
