package com.applydigital.application.usecases.news;

import com.applydigital.application.model.NewsGetResponseDTO;
import com.applydigital.domain.model.NewsEntity;

import java.time.Month;
import java.util.List;

public interface INewsService {

    public List<NewsGetResponseDTO> findNewsByComplexCriteria(String author, List<String> tags, String storyTitle, Month month);
     public NewsGetResponseDTO getNewsByObjectId(String objectID);
    public void deleteNewsById(String objectId);
  }
