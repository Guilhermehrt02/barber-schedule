package com.unifei.barber_schedule.domain.barber;

import com.unifei.barber_schedule.domain.user.User;
import com.unifei.barber_schedule.domain.user.UserRole;
import com.unifei.barber_schedule.entity.Appointment;
import com.unifei.barber_schedule.entity.Service;
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
        super(name, email, phone, password, UserRole.BARBER); // Chama o construtor da superclasse
    }

}
