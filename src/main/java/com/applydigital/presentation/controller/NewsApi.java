package com.applydigital.presentation.controller;

import com.applydigital.application.model.NewsGetResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Month;
import java.util.List;

@RequestMapping(value = "/api/v1/news")
public interface NewsApi {


    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<NewsGetResponseDTO>> getAllNews(@RequestHeader(value = "X-Service-Token", required = false) String token,
            @RequestParam(name = "author", required=false) String author,
            @RequestParam(name = "tags", required=false) List<String> tags,
            @RequestParam(name = "storytitle", required=false) String storyTitle,
            @RequestParam(name = "month", required=false) Month month);
    @RequestMapping(value = "/{objectId}", method = RequestMethod.GET)
    ResponseEntity<NewsGetResponseDTO> getNewsByObjectId(@PathVariable String objectId);
    @RequestMapping(value = "/{objectId}", method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteNewsById(@RequestHeader("X-Service-Token") String token,
                                        @PathVariable String objectId);


}
