package com.app.deseadostores.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "stores")
@Data
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private User owner;
    private List<String> images; //url images
    private String address;
    private String googleMapsUrl;
    private String description;
    private String webpageUrl;
    private List<String> socialMediaLinks;
    //private List<Offer> offers;
    private List<String> categories;

    public Store() {}

    public Store(String name, User owner, String address, String description) {
        this.name = name;
        this.owner = owner;
        this.address = address;
        this.description = description;
    }
}
