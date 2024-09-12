package com.app.deseadostores.dto.offerDto;

import lombok.Getter;

@Getter
public class OfferRequestDto {
    private String title;
    private String description;
    private double price;

    public OfferRequestDto() {}

    public OfferRequestDto(String title, String description, double price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }
}
