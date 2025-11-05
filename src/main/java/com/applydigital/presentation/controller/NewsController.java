package com.applydigital.presentation.controller;

import com.applydigital.application.model.NewsGetRequestDTO;
import com.applydigital.domain.model.NewsDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class NewsController implements NewsApi {


    @Override
    public ResponseEntity<List<NewsDTO>> getAllNews(NewsGetRequestDTO request) {
        return null;
    }

    @Override
    public ResponseEntity<NewsDTO> getNewsByObjectId(String objectID) {
        return null;
    }


    @Override
    public ResponseEntity<Void> deleteNewsById(String objectId) {
        return null;
    }
}
