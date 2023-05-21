package com.learn_security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutResponse {
    private String name;
    private Double total;
    private String paymentId;
    private String paymentStatus;
    private String createAt;
}
