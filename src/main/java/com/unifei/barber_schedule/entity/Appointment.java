package com.unifei.barber_schedule.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


//INCOMPLETA - ALTERAR OS ENDPOINTS



@Entity
@Getter
@Setter
@NoArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private String time;

    @ManyToOne // Many appointments can have one service
    @JoinColumn(name = "service_id", nullable = false) // The column name in the database
    private Service service;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

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
