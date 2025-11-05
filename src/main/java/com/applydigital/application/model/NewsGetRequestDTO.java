package com.applydigital.application.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Month;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Optional filters for searching news")
public class NewsGetRequestDTO {



    @Schema(description = "Author of the news", example = "John Doe")
    private String author;

    @Schema(description = "Tags associated with the news", example = "[\" Apply digital is the first tech company in innovation\"]")
    private List<String> tags;

    @Schema(description = "Part or full title of the story", example = "Joe Doe is president")
    private String storyTitle;

    @Schema(description = "Month e.g. JANUARY, FEBRUARY", example = "SEPTEMBER")
    private Month month;
}





