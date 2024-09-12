package com.app.deseadostores.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Store> stores = new LinkedHashSet<>();

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
