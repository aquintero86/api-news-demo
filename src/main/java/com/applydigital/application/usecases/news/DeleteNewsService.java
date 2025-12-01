package com.applydigital.application.usecases.news;

import com.applydigital.application.exception.NewsNotFoundException;
import com.applydigital.application.repository.DeletedNewsRepository;
import com.applydigital.application.repository.NewsRepository;
import com.applydigital.domain.model.DeletedNewsEntity;
import com.applydigital.domain.model.NewsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.function.Supplier;

import static java.time.LocalDateTime.*;

@Service
public class DeleteNewsService implements IDeleteNewsService{

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private DeletedNewsRepository blacklistRepository;


    @Override
    public void deleteNewsById(String objectId) {

        NewsEntity record =  Optional.ofNullable(newsRepository.findByObjectId(objectId))
                .orElseThrow(() -> new NewsNotFoundException("Object id not found!"));
        Long deleted_news = newsRepository.deleteByObjectId(record.getObjectId());
        LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.HOURS);
        DeletedNewsEntity deletedNews = new DeletedNewsEntity(objectId, now);
        blacklistRepository.save( deletedNews);
    }
}
