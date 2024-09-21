package com.unifei.barber_schedule.service;

import com.unifei.barber_schedule.domain.user.UserRole;
import com.unifei.barber_schedule.domain.admin.Admin;
import com.unifei.barber_schedule.repository.AdminRepository;
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

    // register a new admin
    public Admin registerAdmin(Admin admin) {

        if (adminRepository.findByEmail(admin.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already registered - " + admin.getEmail());
        }

        admin.setId(0); // Ensure the admin is new
        admin.setRole(UserRole.ADMIN);

        return adminRepository.save(admin);
    }

    //get admin by id
    public Admin getAdminById(int id) {

        return adminRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Admin not found with id - " + id));
    }

    // get all admins
    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    //update admin
    public Admin updateAdmin(Admin updatedAdmin) {

        Optional<Admin> result = adminRepository.findByEmail(updatedAdmin.getEmail());

        if (result.isPresent() && result.get().getId() != updatedAdmin.getId()) {
            throw new IllegalArgumentException("Email already registered - " + updatedAdmin.getEmail());
        }

        Admin existingAdmin = getAdminById(updatedAdmin.getId());
        existingAdmin.setName(updatedAdmin.getName());
        existingAdmin.setEmail(updatedAdmin.getEmail());
        existingAdmin.setPassword(updatedAdmin.getPassword());

        return adminRepository.save(existingAdmin);
    }

    //delete admin
    public void deleteAdmin(int adminId) {

        Admin admin = getAdminById(adminId); // Will throw exception if not found
        adminRepository.deleteById(adminId);
    }


}
