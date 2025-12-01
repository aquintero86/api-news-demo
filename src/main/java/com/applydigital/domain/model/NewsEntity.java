package com.applydigital.domain.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Document(collection = "myCollection")
public class NewsEntity {

    private String objectId;
    private String author;
    private String commentText;
    private String storyTitle;
    private String storyUrl;
    private List<String> tags;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long parentId;
    private Long storyId;

}

