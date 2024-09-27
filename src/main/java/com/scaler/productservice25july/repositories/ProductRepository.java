package com.scaler.productservice25july.repositories;

import com.scaler.productservice25july.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {

    @Override
    List<Product> findAll();

    Product save(Product product);

    List<Product> findByTitleContaining(String query);

    Page<Product> findByTitleContainingAndCategory_Id(String title, Long categoryId, Pageable pageable);
}
