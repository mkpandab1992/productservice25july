
package com.scaler.productservice25july.controllers;
import com.scaler.productservice25july.dtos.products.*;
import com.scaler.productservice25july.exceptions.ProductNotFoundException;
import com.scaler.productservice25july.models.Product;
import com.scaler.productservice25july.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
//    @Value("${productService}")
//    private String productServiceType;

    public ProductController(@Qualifier("dbProductService")ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("")
    public CreateProductResponseDto createProduct(@RequestBody CreateProductRequestDto createProductRequestDto){
        Product product = productService.createProduct(createProductRequestDto.toProduct());
        return CreateProductResponseDto.fromProduct(product);
    }
    @GetMapping("")
    public GetAllProductsResponseDto getAllProducts() {
        List<Product> products = productService.getAllProducts();
        GetAllProductsResponseDto response = new GetAllProductsResponseDto();

        List<GetProductDto> getProductResponseDtos = new ArrayList<>();

        for (Product product: products) {
            getProductResponseDtos.add(GetProductDto.fromProduct(product));
        }

        response.setProducts(getProductResponseDtos);

        return response;
    }

    @GetMapping("/{id}")
    public GetProductResponseDto getSingleProduct(@PathVariable("id") Long id) {
        Product product = productService.getProduct(id);
        GetProductResponseDto response = new GetProductResponseDto();
        response.setProduct(GetProductDto.fromProduct(product));
        return response;
    }

    @PatchMapping("/{id}")
    public PatchProductResponseDto updateProduct(@PathVariable("id") Long productId,@RequestBody CreateProductDto productDto){
        Product product = null;
        try {
            product = productService.partialUpdateProduct(productId,productDto.toProduct());
        } catch (ProductNotFoundException e) {
            throw new RuntimeException(e);
        }
        PatchProductResponseDto response = new PatchProductResponseDto();
        response.setProduct(GetProductDto.fromProduct(product));
        return response;
    }
    @DeleteMapping("/{id}")
    public GetProductResponseDto deleteProduct(@PathVariable("id") Long id) {
        Product product = null;
        try {
            product = productService.deleteProduct(id);
        } catch (ProductNotFoundException e) {
            throw new RuntimeException(e);
        }
        GetProductResponseDto response = new GetProductResponseDto();
        response.setProduct(GetProductDto.fromProduct(product));
        return response;
    }

//    public void replaceProduct() {}

    @RequestMapping(name = "NAMAN")
    public String tabgushijhd() {
        return "Magic";
    }
    /*Implemented by using ControllerAdvice*/
   /* @ExceptionHandler(RuntimeException.class)
    public ErrorResponseDto handleRunTimeException(){
        ErrorResponseDto errorResponseDto =new ErrorResponseDto();
        errorResponseDto.setStatus("Error");
        errorResponseDto.setMessage("Runtime Exception");
        return errorResponseDto;
    }
    @ExceptionHandler(Exception.class)
    public String handleException(){

        return "Something Went Wrong";
    }*/

}