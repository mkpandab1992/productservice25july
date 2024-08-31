package com.scaler.productservice25july.repositories;

import com.scaler.productservice25july.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {

    @Override
    List<Product> findAll();

    Optional<Product> findById(long id);

    Product save(Product product);
}
