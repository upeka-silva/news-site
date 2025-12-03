package com.example.demo.dto;

import lombok.Data;
import java.util.Set;

@Data
public class NewsRequest {
    private String headline;
    private String content;
    private Set<Long> categoryIds;
}