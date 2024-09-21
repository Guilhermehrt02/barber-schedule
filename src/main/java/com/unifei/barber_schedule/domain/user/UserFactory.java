package com.unifei.barber_schedule.domain.user;

public interface UserFactory {
    User createUser(RegisterDTO data, String encryptedPassword);
}

