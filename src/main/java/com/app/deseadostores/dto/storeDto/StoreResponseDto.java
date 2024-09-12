package com.app.deseadostores.dto.storeDto;

import com.app.deseadostores.model.User;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

import java.util.List;

@Getter
public class StoreResponseDto {
    private Long id;
    private String name;
    private String address;
    private String googleMapsUrl;
    private String description;
    private String webpageUrl;
    private List<String> socialMediaLinks;
    //private List<Offer> offers;
    private List<String> categories;

    public StoreResponseDto() {}
}
