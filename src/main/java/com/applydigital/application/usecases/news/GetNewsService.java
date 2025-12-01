package com.applydigital.application.usecases.news;


import com.applydigital.application.exception.NewsNotFoundException;
import com.applydigital.application.model.NewsGetResponseDTO;
import com.applydigital.application.repository.NewsRepository;
import com.applydigital.domain.model.NewsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GetNewsService implements IGetNewsService {

    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<NewsGetResponseDTO> findNewsByComplexCriteria(String author, List<String> tags, String storyTitle, Month month) {
        Query query = new Query();
        int year = 2025;
        Optional.ofNullable(author)
                .filter(a -> !a.isEmpty())
                .ifPresent(a ->query.addCriteria(Criteria.where("author").is(a)));
        Optional.ofNullable(tags)
                .filter(t -> !t.isEmpty())
                .ifPresent(t ->query.addCriteria(Criteria.where("_tags").in(t)));
        Optional.ofNullable(storyTitle)
                .filter(s -> !s.isEmpty())
                .ifPresent(s ->query.addCriteria(Criteria.where("storyTitle").regex(s, "i")));
        Optional.ofNullable(month)
                .ifPresent(m ->{
                        String dateStr = m.getValue()< 10 ?  String.format("%d-0%d",  year, m.getValue()) : String.format("%d-%d",  year, m.getValue());
                        YearMonth ym = YearMonth.parse( dateStr);
                        LocalDateTime start = ym.atDay(1).atStartOfDay();
                        LocalDateTime end = ym.atEndOfMonth().atTime(LocalTime.MAX);
                        query.addCriteria(Criteria.where("createdAt").gte(start).lte(end));}
                );


        List<NewsGetResponseDTO> response = Optional.ofNullable(mongoTemplate.find(query, NewsEntity.class)).orElseThrow(() -> new NewsNotFoundException("Object id not found!")).stream()
                .map(field -> new NewsGetResponseDTO(
                        field.getAuthor(),
                        field.getCommentText(),
                        field.getStoryTitle(),
                        field.getStoryUrl(),
                        field.getTags(),
                        field.getCreatedAt(),
                        field.getObjectId()
                ))
                .collect(Collectors.toList());

        return Optional.ofNullable(response)
                .orElseThrow(() -> new NewsNotFoundException("Object id not found!"));

    }




    @Override
    public NewsGetResponseDTO getNewsByObjectId(String objectID) {
        NewsEntity news = Optional.ofNullable(newsRepository.findByObjectId(objectID))
                .orElseThrow(() -> new NewsNotFoundException("Object id not found!"));
        return  new NewsGetResponseDTO(
                news.getAuthor(),
                news.getCommentText(),
                news.getStoryTitle(),
                news.getStoryUrl(),
                news.getTags(),
                news.getCreatedAt(),
                news.getObjectId());
    }


}
