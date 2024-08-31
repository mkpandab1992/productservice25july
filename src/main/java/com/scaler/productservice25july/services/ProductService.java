package com.scaler.productservice25july.services;

import com.scaler.productservice25july.exceptions.ProductNotFoundException;
import com.scaler.productservice25july.models.Product;

import java.util.List;

public interface ProductService {
    public Product createProduct(Product  product);

    List<Product> getAllProducts();

    Product getProduct(Long id);

    Product partialUpdateProduct(Long productId, Product product) throws ProductNotFoundException;

    Product deleteProduct(Long id) throws ProductNotFoundException;
}
