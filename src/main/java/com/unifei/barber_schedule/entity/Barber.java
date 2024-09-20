package com.unifei.barber_schedule.entity;

import com.unifei.barber_schedule.security.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("BARBER")
@Getter
@Setter
@NoArgsConstructor
public class Barber extends User {

    @OneToMany(mappedBy = "barber", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> appointments = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "barber_service",
            joinColumns = @JoinColumn(name = "barber_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private List<Service> services = new ArrayList<>();

    public Barber(@NotBlank String name, @NotBlank String email, String phone, String password) {
        super(name, email, phone, password, Role.BARBER); // Chama o construtor da superclasse
    }

}
