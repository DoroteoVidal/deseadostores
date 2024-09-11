package com.app.deseadostores.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastname;
    private String email;
    private String password;
    private String address;
    private LocalDate dateOfBirth;
    private Long dni;
    private Long phone;
    private boolean enabled;

    public User() {}

    public User(String name, String lastname, String email, LocalDate dateOfBirth,
                Long dni, Long phone, String address, String password) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.dni = dni;
        this.phone = phone;
        this.enabled = true;
        this.address = address;
        this.password = password;
    }
}
