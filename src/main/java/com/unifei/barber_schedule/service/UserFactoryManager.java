package com.unifei.barber_schedule.service;

import com.unifei.barber_schedule.domain.admin.AdminFactory;
import com.unifei.barber_schedule.domain.barber.BarberFactory;
import com.unifei.barber_schedule.domain.client.ClientFactory;
import com.unifei.barber_schedule.domain.user.RegisterDTO;
import com.unifei.barber_schedule.domain.user.User;
import com.unifei.barber_schedule.domain.user.UserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserFactoryManager {

    private final Map<String, UserFactory> factories = new HashMap<>();

    @Autowired
    public UserFactoryManager(List<UserFactory> factoryList) {
        for (UserFactory factory : factoryList) {
            if (factory instanceof AdminFactory) {
                factories.put("ADMIN", factory);
            }
            else if (factory instanceof BarberFactory) {
                factories.put("BARBER", factory);
            }
            else if (factory instanceof ClientFactory) {
                factories.put("CLIENT", factory);
            }
        }
    }

    public User createUser(RegisterDTO data, String encryptedPassword) {
        UserFactory factory = factories.get(data.role().toUpperCase());
        if (factory == null) {
            throw new IllegalArgumentException("Invalid user type: " + data.role());
        }
        return factory.createUser(data, encryptedPassword);
    }
}
