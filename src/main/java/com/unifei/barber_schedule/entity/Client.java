package com.unifei.barber_schedule.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a client in the barber scheduling system.
 * A client can have multiple appointments.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Client extends User {

    /**
     * The list of appointments associated with this client.
     * A client can have many appointments, and if the client is removed,
     * all associated appointments are also removed.
     */
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appointment> appointments = new ArrayList<>();

    /**
     * Creates a new client with the specified name, email, phone, and password.
     *
     * @param name     the name of the client
     * @param email    the email address of the client
     * @param phone    the phone number of the client (optional)
     * @param password the password for the client's account
     */
    public Client(String name, String email, String phone, String password) {
        super(name, email, phone, password); // Calls the constructor of the superclass
    }

}
