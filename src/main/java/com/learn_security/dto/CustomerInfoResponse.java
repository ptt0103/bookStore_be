package com.learn_security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerInfoResponse {
    private String name;
    private String email;
    private String address;
    private String telephoneNumber;
}
