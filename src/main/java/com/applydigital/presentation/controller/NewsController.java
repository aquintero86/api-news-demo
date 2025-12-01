package com.applydigital.presentation.controller;

import com.applydigital.application.model.NewsGetResponseDTO;
import com.applydigital.application.usecases.news.IDeleteNewsService;
import com.applydigital.application.usecases.news.IGetNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.Month;
import java.util.List;

@RestController
public class NewsController implements NewsApi {

    @Autowired
    private IGetNewsService newsService;

    @Autowired
    private IDeleteNewsService deleteNewsService;



    @Override
    public ResponseEntity<List<NewsGetResponseDTO>> getAllNews(String author, List<String> tags, String storyTitle, Month month) {
        List<NewsGetResponseDTO> response = newsService.findNewsByComplexCriteria(author,tags,storyTitle,month);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<NewsGetResponseDTO> getNewsByObjectId(String objectId) {
        NewsGetResponseDTO news = newsService.getNewsByObjectId(objectId);
       return ResponseEntity.ok(news);
    }


    @Override
    public ResponseEntity<Void> deleteNewsById(String objectId) {
        deleteNewsService.deleteNewsById(objectId);
        return ResponseEntity.ok().build();
    }




}
