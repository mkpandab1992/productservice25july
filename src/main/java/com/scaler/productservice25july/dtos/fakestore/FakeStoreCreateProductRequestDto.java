package com.scaler.productservice25july.dtos.fakestore;

import com.scaler.productservice25july.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreCreateProductRequestDto {
    private String title;
    private String description;
    private String imageUrl;
    private String category;
    private  double price;

    public static FakeStoreCreateProductRequestDto fromProduct(Product product) {
        FakeStoreCreateProductRequestDto fakeStoreCreateProductRequestDto = new FakeStoreCreateProductRequestDto();
        fakeStoreCreateProductRequestDto.setTitle(product.getTitle());
        fakeStoreCreateProductRequestDto.setDescription(product.getDescription());
        fakeStoreCreateProductRequestDto.setPrice(product.getPrice());
        fakeStoreCreateProductRequestDto.setDescription(product.getDescription());
        fakeStoreCreateProductRequestDto.setImageUrl(product.getImageUrl());
        fakeStoreCreateProductRequestDto.setCategory(product.getCategory().getName());
        return fakeStoreCreateProductRequestDto;
    }
}
