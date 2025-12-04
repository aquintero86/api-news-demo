package com.applydigital.application.repository;

import com.applydigital.domain.model.NewsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface NewsRepository extends MongoRepository<NewsEntity, String> {

    List<NewsEntity>findByAuthorOrTagsOrStoryTitle(String author, List<String> tags, String storyTitle);
    NewsEntity findByObjectId(String objectID);
    Long deleteByObjectId(String objectID);

}
