package com.scaler.productservice25july.dtos.products;

import com.scaler.productservice25july.models.Category;
import com.scaler.productservice25july.models.Product;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateProductRequestDto {
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private String categoryName;
    private  double price;

    public static CreateProductRequestDto fromProduct(){
        return null;
    }

    public Product toProduct() {
        Product product = new Product();
        product.setTitle(this.title);
        product.setDescription(this.description);
        product.setPrice(this.price);
        product.setImageUrl(this.imageUrl);
        Category category = new Category();
        category.setName(categoryName);
        product.setCategory(category);

        return product;
    }
}
