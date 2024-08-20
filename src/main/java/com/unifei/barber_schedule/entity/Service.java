package com.unifei.barber_schedule.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Service {

    @Id
    //It's the primary key. Not recommend to use email as primary key because it can be changed by the user and it's not a good practice.
    @GeneratedValue(strategy= GenerationType.IDENTITY) // Auto increment
    private int id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;

    @NotNull(message = "Price must not be null")
    @Min(value = 0, message = "Price must be a positive number")
    @Column(nullable = false)
    private Double price;

    @NotNull(message = "Duration must not be null")
    @Min(value = 0, message = "Duration must be a positive number")
    @Column(nullable = false)
    private Integer duration; // In minutes

    @ManyToMany(mappedBy = "services")
    private List<Barber> barbers = new ArrayList<>();

    public Service(String name, Double price, Integer duration) {
        this.name = name;
        this.price = price;
        this.duration = duration;
    }

}
