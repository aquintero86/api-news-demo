package com.applydigital.presentation.controller;

import com.applydigital.application.model.NewsGetRequestDTO;
import com.applydigital.domain.model.NewsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RequestMapping(value = "/api/v1/news")
public interface NewsApi {


    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<NewsDTO>> getAllNews(NewsGetRequestDTO request);
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<NewsDTO> getNewsByObjectId(@PathVariable String objectID);
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteNewsById(@PathVariable String objectId);
}
