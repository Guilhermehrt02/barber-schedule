package com.unifei.barber_schedule.service;

import com.unifei.barber_schedule.entity.Admin;
import com.unifei.barber_schedule.repository.AdminRepository;
import com.unifei.barber_schedule.repository.AppointmentRepository;
import com.unifei.barber_schedule.repository.BarberRepository;
import com.unifei.barber_schedule.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    // CRUD methods. Service layer calls the repository layer methods already implemented.

    // find all admins
    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    // find admin by id
    public Admin findById(int id){
        Optional<Admin> result = adminRepository.findById(id);

        Admin admin = null;

        if (result.isPresent()) {
            admin = result.get();
        } else {
            throw new RuntimeException("Admin id not found - " + id);
        }

        return admin;
    }

    // save admin
    public Admin save(Admin admin) {
        return adminRepository.save(admin);
    }

    // delete admin
    public void deleteById(int id){
        adminRepository.deleteById(id);
    }

}
