package com.applydigital.domain.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@Document(collection = "news_blacklist")
@Data
public class DeletedNewsEntity {
    private String objectId;
    private LocalDateTime createdAt;
}