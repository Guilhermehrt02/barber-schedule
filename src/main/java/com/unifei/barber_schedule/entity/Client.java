package com.unifei.barber_schedule.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Client {

    @Id //It's the primary key. Not recommend to use email as primary key because it can be changed by the user and it's not a good practice.
    @GeneratedValue(strategy= GenerationType.IDENTITY) // Auto increment
    private int id;

    @NotBlank // Ensures the name is not null, not empty, and contains non-whitespace characters
    @Column(nullable = false) // Ensures the name column cannot be null in the database
    private String name;

    @NotBlank
    @Email // Ensures the email is a valid email address
    @Column(nullable = false, unique = true, length = 255)
    private String email;

    private String phone;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true) // One client can have many appointments
    private List<Appointment> appointments = new ArrayList<>();

    public Client(String name, String email, String phone, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

}
