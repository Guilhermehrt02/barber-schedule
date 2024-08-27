package com.unifei.barber_schedule.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(nullable = false)
    private String date;

    @NotNull
    @Column(nullable = false)
    private String time;

    @NotNull
    @ManyToOne // Many appointments can have one service
    @JoinColumn(name = "service_id", nullable = false) // The column name in the database
    private Service service;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "barber_id", nullable = false)
    private Barber barber;

    public Appointment(String date, String time, Service service, Client client, Barber barber) {
        this.date = date;
        this.time = time;
        this.service = service;
        this.client = client;
        this.barber = barber;
    }

}
