package com.unifei.barber_schedule.entity;

import com.unifei.barber_schedule.security.Role;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("ADMIN")
@Getter
@Setter
@NoArgsConstructor
public class Admin extends User{

    public Admin(@NotBlank String name, @NotBlank String email, String phone, String password) {
        super(name, email, phone, password, Role.ADMIN); // Chama o construtor da superclasse
    }

}
