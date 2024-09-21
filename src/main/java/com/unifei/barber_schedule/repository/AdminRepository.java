package com.unifei.barber_schedule.repository;

import com.unifei.barber_schedule.domain.admin.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Optional<Admin> findByEmail(String email);
    // This interface extends JpaRepository, which is a Spring Data JPA interface that provides CRUD methods
}
