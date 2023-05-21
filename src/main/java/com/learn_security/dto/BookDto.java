package com.learn_security.dto;

import com.learn_security.models.Genre;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class BookDto {
    private String name;
    private Long price;
    private String imageUrl;
    private String author;
    private String publisher;
    private Long genreId;
}
