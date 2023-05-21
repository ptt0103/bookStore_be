package com.learn_security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionDto {
    String message;

    public static ExceptionDto of(Exception e){
        return new ExceptionDto(e.getMessage());
    }
}
