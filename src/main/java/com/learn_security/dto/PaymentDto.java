package com.learn_security.dto;

import lombok.Data;

import java.util.List;

@Data
public class PaymentDto {
    List<Long> orderIds;
    public Double total;
    public String cancelUrl;
    public String returnUrl;
}
