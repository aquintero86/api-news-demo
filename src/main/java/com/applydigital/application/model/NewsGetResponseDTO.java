package com.applydigital.application.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsGetResponseDTO {
    @JsonProperty("author")
    private String author;
    @JsonProperty("commentText")
    private String commentText;
    @JsonProperty("storyTitle")
    private String storyTitle;
    @JsonProperty("storyUrl")
    private String storyUrl;
    @JsonProperty("tags")
    private List<String> tags;
    @JsonProperty("createDate")
    private LocalDateTime createdAt;
    @JsonProperty("objectId")
    private String objectId;
}
