package com.scaler.productservice25july.dtos.fakestore;

import com.scaler.productservice25july.models.Category;
import com.scaler.productservice25july.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreGetProductResponseDto {
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private Category category;
    private  double price;

    public Product toProduct(){

        Product product=new Product();
        product.setId(this.getId());
        product.setTitle(this.getTitle());
        product.setDescription(this.getDescription());
        product.setPrice(this.getPrice());
        product.setImageUrl(this.getImageUrl());
        product.setCategory(this.getCategory());

        return product;
    }
}
