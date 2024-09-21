package com.unifei.barber_schedule.domain.barber;

import com.unifei.barber_schedule.domain.user.RegisterDTO;
import com.unifei.barber_schedule.domain.user.User;
import com.unifei.barber_schedule.domain.user.UserFactory;
import org.springframework.stereotype.Service;

@Service
public class BarberFactory implements UserFactory {

    @Override
    public User createUser(RegisterDTO data, String encryptedPassword) {
        return new Barber(data.name(), data.email(), data.phone(), encryptedPassword);
    }

}
