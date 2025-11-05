package com.applydigital.domain.model;


import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Data
public class NewsDTO {
    private String author;
    private String commentText;
    private String storyTitle;
    private String storyUrl;
    private List<String> tags;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String objectId;
    private Long parentId;
    private Long storyId;

}

