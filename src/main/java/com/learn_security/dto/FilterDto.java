package com.learn_security.dto;

import com.learn_security.models.Genre;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class FilterDto {
    List<Long> genreIds;
}
