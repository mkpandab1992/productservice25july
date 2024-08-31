package com.scaler.productservice25july.services;

import com.scaler.productservice25july.dtos.fakestore.FakeStoreCreateProductRequestDto;
import com.scaler.productservice25july.dtos.fakestore.FakeStoreGetProductResponseDto;
import com.scaler.productservice25july.models.Product;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class ProductServiceFakestoreImpl implements  ProductService{

    private RestTemplate restTemplate;
    public ProductServiceFakestoreImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public Product createProduct(Product product) {
        FakeStoreCreateProductRequestDto fakeStoreCreateProductRequestDto = FakeStoreCreateProductRequestDto.fromProduct(product);
        FakeStoreGetProductResponseDto response =restTemplate.postForObject("https://fakestoreapi.com/products", fakeStoreCreateProductRequestDto, FakeStoreGetProductResponseDto.class);
        return response.toProduct();
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreGetProductResponseDto[] response =restTemplate.getForObject(
                "https://fakestoreapi.com/products", FakeStoreGetProductResponseDto[].class);
//        List<FakeStoreGetProductResponseDto> fakeStoreGetProductResponseDtoList =   Stream.of(response).toList();
//        throw new RuntimeException();
        List<Product> products = new ArrayList<>();
        for(FakeStoreGetProductResponseDto fakeStoreGetProductResponseDto : response){
            products.add(fakeStoreGetProductResponseDto.toProduct());
        }
        return products;
    }

    @Override
    public Product getProduct(Long id) {
        FakeStoreGetProductResponseDto fakeStoreGetProductResponseDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id, FakeStoreGetProductResponseDto.class
        );
        return fakeStoreGetProductResponseDto.toProduct();
    }

    @Override
    public Product partialUpdateProduct(Long productId, Product product) {

        HttpEntity<FakeStoreCreateProductRequestDto> requestEntity = new HttpEntity<>(FakeStoreCreateProductRequestDto.fromProduct(product));

        ResponseEntity<FakeStoreGetProductResponseDto> responseEntity = restTemplate.exchange(
                "https://fakestoreapi.com/products/" + productId,
                HttpMethod.PATCH,
                requestEntity,
                FakeStoreGetProductResponseDto.class
        );
        throw new RuntimeException();
//        return responseEntity.getBody().toProduct();
    }

    @Override
    public Product deleteProduct(Long id) {
        FakeStoreGetProductResponseDto fakeStoreGetProductResponseDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id, FakeStoreGetProductResponseDto.class
        );
        restTemplate.delete("https://fakestoreapi.com/products/" + id, FakeStoreGetProductResponseDto.class);
        return fakeStoreGetProductResponseDto.toProduct();
    }
}
