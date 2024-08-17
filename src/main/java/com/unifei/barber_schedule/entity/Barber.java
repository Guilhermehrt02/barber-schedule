package com.unifei.barber_schedule.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
public class Barber {

    @Id
    //It's the primary key. Not recommend to use email as primary key because it can be changed by the user and it's not a good practice.
    @GeneratedValue(strategy= GenerationType.IDENTITY) // Auto increment
    private int id;

    @NotBlank // Ensures the name is not null, not empty, and contains non-whitespace characters
    @Column(nullable = false) // Ensures the name column cannot be null in the database
    private String name;

    private String phone;

    @OneToMany(mappedBy = "barber", cascade = CascadeType.ALL, orphanRemoval = true) // One barber can have many appointments
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "barber", cascade = CascadeType.ALL, orphanRemoval = true) // One barber can provide many services
    private List<Service> services;

    public Barber() {
    }

    public Barber(@NotBlank String name, String phone, List<Service> services) {
        this.name = name;
        this.phone = phone;
        this.services = services;
    }

    public @NotBlank String getName() {
        return name;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    @Override
    public String toString() {
        return "Barber{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", appointments=" + appointments +
                ", services=" + services +
                '}';
    }

}
