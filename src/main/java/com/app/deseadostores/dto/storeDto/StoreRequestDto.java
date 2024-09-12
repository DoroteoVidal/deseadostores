package com.app.deseadostores.dto.storeDto;

import lombok.Getter;

import java.util.List;

@Getter
public class StoreRequestDto {
    private String name;
    private List<String> images;
    private String address;
    private String googleMapsUrl;
    private String description;
    private String webpageUrl;
    private List<String> socialMediaLinks;
    private List<String> categories;

    public StoreRequestDto() {}

    public StoreRequestDto(String name, String address, String description) {
        this.name = name;
        this.address = address;
        this.description = description;
    }

    public StoreRequestDto(String name, List<String> images, String address,
                           String googleMapsUrl, String description, String webpageUrl,
                           List<String> socialMediaLinks, List<String> categories) {
        this.name = name;
        this.images = images;
        this.address = address;
        this.googleMapsUrl = googleMapsUrl;
        this.description = description;
        this.webpageUrl = webpageUrl;
        this.socialMediaLinks = socialMediaLinks;
        this.categories = categories;
    }
}
