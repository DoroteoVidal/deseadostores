package com.app.deseadostores.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "offers")
@Data
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private double price;

    public Offer() {}

    public Offer(String title, String description, double price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }
}
