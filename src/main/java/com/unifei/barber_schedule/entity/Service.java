package com.unifei.barber_schedule.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Service {

    @Id
    //It's the primary key. Not recommend to use email as primary key because it can be changed by the user and it's not a good practice.
    @GeneratedValue(strategy= GenerationType.IDENTITY) // Auto increment
    private int id;

    @NotBlank(message = "Service name must not be blank")
    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int duration; // Duration in minutes, for example

    @ManyToOne // Many services can be provided by one barber
    @JoinColumn(name = "barber_id", nullable = false)
    private Barber barber;

    @Column(nullable = false)
    private boolean active = true;

    @Column(nullable = false)
    private boolean deleted = false;

    public Service() {
    }

    public Service(@NotBlank(message = "Service name must not be blank") String name, double price, int duration, Barber barber) {
        this.name = name;
        this.price = price;
        this.duration = duration;
        this.barber = barber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotBlank(message = "Service name must not be blank") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Service name must not be blank") String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Barber getBarber() {
        return barber;
    }

    public void setBarber(Barber barber) {
        this.barber = barber;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", duration=" + duration +
                ", barber=" + barber +
                ", active=" + active +
                ", deleted=" + deleted +
                '}';
    }

}
