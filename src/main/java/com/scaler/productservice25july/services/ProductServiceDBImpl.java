package com.scaler.productservice25july.services;

import com.scaler.productservice25july.exceptions.ProductNotFoundException;
import com.scaler.productservice25july.models.Category;
import com.scaler.productservice25july.models.Product;
import com.scaler.productservice25july.repositories.CategoryRepository;
import com.scaler.productservice25july.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("dbProductService")
public class ProductServiceDBImpl implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public ProductServiceDBImpl(ProductRepository productRepository,
                                CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Product createProduct(Product product) {
        Category toBePutInProduct = getCategoryToBeInProduct(product);

        product.setCategory(toBePutInProduct);

        Product savedProduct = productRepository.save(product);
        System.out.println("hahahhahaha");

        return savedProduct;
    }

    @Override
    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Long id) {

       Optional<Product> productOptional = Optional.of(productRepository.getById(id));
       return productOptional.orElse(null);
    }

    @Override
    public Product partialUpdateProduct(Long productId, Product product) throws ProductNotFoundException {
        Optional<Product> optionalProduct= productRepository.findById(productId);
        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException();
        }
        Product productToupdate = optionalProduct.get();
        if(product.getDescription() !=null)
            productToupdate.setDescription(product.getDescription());
        if(product.getTitle() !=null)
            productToupdate.setTitle(product.getTitle());
        if(product.getPrice() !=null)
            productToupdate.setPrice(product.getPrice());
        if(product.getCategory() !=null) {
            Category toBePutInProduct = getCategoryToBeInProduct(product);
            productToupdate.setCategory(toBePutInProduct);
        }
        return productRepository.save(productToupdate);
    }

    @Override
    public Product deleteProduct(Long id) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepository.findById(id);
        if(productOptional.isEmpty()){
           throw new ProductNotFoundException();
        }
        Product productTodelete = productOptional.get();
        productRepository.delete(productTodelete);
        return productTodelete;
    }

    private Category getCategoryToBeInProduct(Product product) {
        String categoryName = product.getCategory().getName();

        Optional<Category> category =
                categoryRepository.findByName(categoryName);
        Category toBePutInProduct = null;

        if (category.isEmpty()) {
            Category toSaveCategory = new Category();
            toSaveCategory.setName(categoryName);

            toBePutInProduct = toSaveCategory;
        } else {
            toBePutInProduct = category.get();
        }
        return toBePutInProduct;
    }
}