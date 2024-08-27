package com.unifei.barber_schedule.controller;

import com.unifei.barber_schedule.entity.Admin;
import com.unifei.barber_schedule.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // Here we can create the methods that will be called by the client side.

    //Get all admins
    @GetMapping
    public List<Admin> findAll() {
        return adminService.findAll();
    }

    //Get admin by id
    @GetMapping("/{adminId}")
    public Admin getAdmin(@PathVariable int adminId) {

        Admin admin = adminService.findById(adminId);

        if (admin == null) {
            throw new RuntimeException("Admin id not found - " + adminId);
        }
        return admin;
    }

    //Create admin
    @PostMapping
    public Admin addAdmin(@RequestBody Admin admin) {

        admin.setId(0); // This way we can save a new admin instead of updating an existing one
        Admin dbAdmin = adminService.save(admin);

        return dbAdmin;
    }

    //Update admin
    @PutMapping
    public Admin updateAdmin(@RequestBody Admin admin) {
        Admin dbAdmin = adminService.save(admin);

        return dbAdmin;
    }

    //Delete admin
    @DeleteMapping("/{adminId}")
    public String deleteAdmin(@PathVariable int adminId) {

        Admin admin = adminService.findById(adminId);

        if (admin == null) {
            throw new RuntimeException("Admin id not found - " + adminId);
        }

        adminService.deleteById(adminId);

        return "Deleted admin id - " + adminId;
    }
}
