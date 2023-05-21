package com.learn_security.dto;

import lombok.Data;

import java.util.List;
@Data
public class BillResponseDTO {
    private String id;
    private String status;
    private List<Link> links;

    @Data
    public class Link{
        private String href;
        private String rel;
        private String method;
    }
}

