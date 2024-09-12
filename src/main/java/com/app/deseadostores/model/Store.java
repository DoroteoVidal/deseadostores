package com.app.deseadostores.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "stores")
@Data
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    private User owner;
    private List<String> images;
    private String address;
    private String googleMapsUrl;
    private String description;
    private String webpageUrl;
    private List<String> socialMediaLinks;

    @OneToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Offer> offers = new LinkedHashSet<>();
    private List<String> categories;

    public Store() {}

    public Store(String name, User owner, String address, String description) {
        this.name = name;
        this.owner = owner;
        this.address = address;
        this.description = description;
    }
}
