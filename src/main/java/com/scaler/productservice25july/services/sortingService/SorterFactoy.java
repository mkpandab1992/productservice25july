package com.scaler.productservice25july.services.sortingService;

import com.scaler.productservice25july.dtos.search.SortingCriteria;

public class SorterFactoy {
    public static Sorter getSorterByCriteriya(SortingCriteria sortingCriteria){
        return switch (sortingCriteria){
            case RELEVANCE -> null;
            case POPULARITY -> null;
            case PRICE_HIGH_TO_LOW -> new PriceHighToLowSorter();
            case PRICE_LOW_TO_HIGH -> new PriceLowToHighSorter();
            case null,default -> null;
        };
    }
}
