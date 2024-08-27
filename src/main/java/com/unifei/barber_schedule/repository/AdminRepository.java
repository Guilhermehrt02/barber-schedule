package com.unifei.barber_schedule.repository;

import com.unifei.barber_schedule.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    // This interface extends JpaRepository, which is a Spring Data JPA interface that provides CRUD methods
}
