package com.applydigital.domain.model;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "news_blacklist")
public class BlacklistEntity {
    @Id
    private String objectId;
    private LocalDateTime createdAt;
}