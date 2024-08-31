package com.scaler.productservice25july.dtos.products;

import com.scaler.productservice25july.models.Product;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateProductResponseDto {
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private String categoryName;
    private  double price;

    public static CreateProductResponseDto fromProduct(Product product) {
        CreateProductResponseDto responseDto = new CreateProductResponseDto();
        responseDto.setId(product.getId());
        responseDto.setDescription(product.getDescription());
        responseDto.setTitle(product.getTitle());
        responseDto.setPrice(product.getPrice());
        responseDto.setImageUrl(product.getImageUrl());
        responseDto.setCategoryName(product.getCategory().getName());
        return responseDto;
    }
}
