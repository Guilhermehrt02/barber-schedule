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
public class Barber {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) // Auto increment
    private int id;

    @NotBlank // Ensures the name is not null, not empty, and contains non-whitespace characters
    @Column(nullable = false) // Ensures the name column cannot be null in the database
    private String name;

    @NotBlank
    @Email // Ensures the email is a valid email address
    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @OneToMany(mappedBy = "barber", cascade = CascadeType.ALL, orphanRemoval = true) // One barber can have many appointments
    private List<Appointment> appointments = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "barber_service",
            joinColumns = @JoinColumn(name = "barber_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private List<Service> services = new ArrayList<>();

    public Barber(@NotBlank String name, String phone) {
        this.name = name;
        this.email = phone;
    }

}
