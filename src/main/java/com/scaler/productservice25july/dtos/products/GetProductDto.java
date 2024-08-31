package com.scaler.productservice25july.dtos.products;

import com.scaler.productservice25july.models.Product;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GetProductDto {
    private Long id;
    private String title;
    private String description;
    private double price;
    private String imageUrl;
    private String categoryName;

    public static GetProductDto fromProduct(Product product) {
        GetProductDto getProductDto = new GetProductDto();
        getProductDto.setId(product.getId());
        getProductDto.setTitle(product.getTitle());
        getProductDto.setDescription(product.getDescription());
        getProductDto.setImageUrl(product.getImageUrl());
        getProductDto.setCategoryName(product.getCategory().getName());
        getProductDto.setPrice(product.getPrice());
        return getProductDto;
    }
}
