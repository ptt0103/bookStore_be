package com.learn_security.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDto {
    private Long bookId;
    private Integer quantity;
}
