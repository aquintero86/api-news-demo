package com.applydigital.presentation.controller;

import ch.qos.logback.core.joran.action.PreconditionValidator;
import com.applydigital.application.model.NewsGetResponseDTO;
import com.applydigital.application.usecases.news.IDeleteNewsService;
import com.applydigital.application.usecases.news.IGetNewsService;
import com.applydigital.domain.security.TokenValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.time.Month;
import java.util.List;

@RestController
public class NewsController implements NewsApi {

    @Autowired
    private IGetNewsService newsService;

    @Autowired
    private IDeleteNewsService deleteNewsService;

    @Autowired
    private TokenValidator validator;



    @Override
    public ResponseEntity<List<NewsGetResponseDTO>> getAllNews( String token, String author, List<String> tags, String storyTitle, Month month) {

        if (!validator.isValid(token)) {
            throw HttpClientErrorException.create(HttpStatus.UNAUTHORIZED,
                    "Unauthorized",
                    null, // HttpHeaders
                    null, // byte[] body
                    null);
        }
        List<NewsGetResponseDTO> response = newsService.findNewsByComplexCriteria(author,tags,storyTitle,month);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<NewsGetResponseDTO> getNewsByObjectId(String objectId) {
        NewsGetResponseDTO news = newsService.getNewsByObjectId(objectId);
       return ResponseEntity.ok(news);
    }


    @Override
    public ResponseEntity<Void> deleteNewsById(String token, String objectId) {
        deleteNewsService.deleteNewsById(objectId);
        return ResponseEntity.ok().build();
    }




}
