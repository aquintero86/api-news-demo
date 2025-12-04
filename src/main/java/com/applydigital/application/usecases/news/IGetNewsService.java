package com.applydigital.application.usecases.news;

import com.applydigital.application.model.NewsGetResponseDTO;

import java.time.Month;
import java.util.List;

public interface IGetNewsService {

    public List<NewsGetResponseDTO> findNewsByComplexCriteria(String author, List<String> tags, String storyTitle, Month month);
     public NewsGetResponseDTO getNewsByObjectId(String objectID);
  }
