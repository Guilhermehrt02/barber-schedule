package com.unifei.barber_schedule.repository;

import com.unifei.barber_schedule.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Integer> {

    UserDetails findByEmail(String email);
}
