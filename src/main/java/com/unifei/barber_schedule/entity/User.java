package com.unifei.barber_schedule.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
 * @MappedSuperclass: Indica que a classe não deve ser mapeada como uma tabela,
 * mas suas propriedades são herdadas pelas classes que a estendem.
 * Herança: As classes filhas herdam os atributos da classe @MappedSuperclass,
 * e essas propriedades são mapeadas nas tabelas das subclasses.
 */
@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Email
    @Column(nullable = false, unique = true, length = 255)
    private String email;

    private String phone;

    @NotNull
    @Column(nullable = false)
    private String password;

    public User(String name, String email, String phone, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

}
