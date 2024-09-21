package com.unifei.barber_schedule.domain.admin;

import com.unifei.barber_schedule.domain.user.RegisterDTO;
import com.unifei.barber_schedule.domain.user.User;
import com.unifei.barber_schedule.domain.user.UserFactory;
import org.springframework.stereotype.Service;

@Service
public class AdminFactory implements UserFactory {

    @Override
    public User createUser(RegisterDTO data, String encryptedPassword) {
        return new Admin(data.name(), data.email(), data.phone(), encryptedPassword);
    }
}
