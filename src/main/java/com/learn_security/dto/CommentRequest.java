package com.learn_security.dto;

import lombok.Data;

@Data
public class CommentRequest {
    private Long bookId;
    private String content;
}
