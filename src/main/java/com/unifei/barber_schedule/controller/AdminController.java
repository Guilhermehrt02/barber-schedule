package com.unifei.barber_schedule.controller;

import com.unifei.barber_schedule.entity.Admin;
import com.unifei.barber_schedule.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // Here we can create the methods that will be called by the client side.

    //register a new admin
    @PostMapping("/register")
    public ResponseEntity<?> registerAdmin(@RequestBody Admin admin) {

        Admin newAdmin = adminService.registerAdmin(admin);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAdmin);
    }


    //Get all admins
    @GetMapping
    public ResponseEntity<List<Admin>> findAll() {
        List<Admin> admins = adminService.findAll();
        return ResponseEntity.ok(admins);
    }

    //Get admin by id
    @GetMapping("/{adminId}")
    public ResponseEntity<Admin> getAdmin(@PathVariable int adminId) {

        Admin admin = adminService.getAdminById(adminId);

        return ResponseEntity.ok(admin);
    }

    //Update admin
    @PutMapping
    public ResponseEntity<Admin> updateAdmin(@RequestBody Admin admin) {

        Admin Admin = adminService.updateAdmin(admin);

        return ResponseEntity.ok(Admin);
    }

    //Delete admin
    @DeleteMapping("/{adminId}")
    public ResponseEntity<String> deleteAdmin(@PathVariable int adminId) {

        adminService.deleteAdmin(adminId);

        return ResponseEntity.ok("Deleted Admin id - " + adminId);
    }
}
