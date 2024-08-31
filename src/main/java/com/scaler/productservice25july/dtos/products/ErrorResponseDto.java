package com.scaler.productservice25july.dtos.products;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponseDto {
    private String status;
    private String message;
}
