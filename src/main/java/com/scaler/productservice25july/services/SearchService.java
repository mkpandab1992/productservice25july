package com.scaler.productservice25july.services;

import com.scaler.productservice25july.dtos.search.FilterDto;
import com.scaler.productservice25july.dtos.search.SortingCriteria;
import com.scaler.productservice25july.models.Product;
import com.scaler.productservice25july.repositories.ProductRepository;
import com.scaler.productservice25july.services.filteringService.FilterFactory;
import com.scaler.productservice25july.services.sortingService.SorterFactoy;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {
    private ProductRepository productRepository;

    public SearchService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> search(String query, List<FilterDto> filters, SortingCriteria sortingCriteria, int pageNumber, int pageSize) {
        List<Product> products = productRepository.findByTitleContaining(query);
        /*Filtered */
        for (FilterDto filterDto: filters) {
            products = FilterFactory.getFilterFromKey(
                    filterDto.getKey()
            ).apply(products, filterDto.getValues());
        }
        /*Sorted the products*/
        products = SorterFactoy.getSorterByCriteriya(sortingCriteria).sort(products);

        /*Pagination*/
        List<Product> productsOnPage = new ArrayList<>();
        for(int i = pageSize*(pageNumber-1); i < (pageNumber*pageSize)-1; i++) {
            productsOnPage.add(products.get(i));
        }
        Pageable pageable=PageRequest.of(pageNumber, pageSize);
        return new PageImpl<>(products, pageable, products.size());
    }

    public Page<Product> simpleSearch(String query,
                 Long categoryId,
                 int pageNumber,
                 int pageSize,
                  String sortingAttribute){
        Page<Product> products = productRepository.findByTitleContainingAndCategory_Id(query,categoryId,
                PageRequest.of(pageNumber, pageSize, Sort.by(sortingAttribute).ascending()));
        Pageable pageable=PageRequest.of(pageNumber, pageSize);
        return products;
    }
}
