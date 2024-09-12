package com.app.deseadostores.dto.offerDto;

import lombok.Getter;

@Getter
public class OfferResponseDto {
    private Long id;
    private String title;
    private String description;
    private double price;

    public OfferResponseDto() {}
}
