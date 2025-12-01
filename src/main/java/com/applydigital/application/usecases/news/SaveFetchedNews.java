package com.applydigital.application.usecases.news;

import com.applydigital.application.model.NewsEnum;
import com.applydigital.application.repository.DeletedNewsRepository;
import com.applydigital.application.repository.NewsRepository;
import com.applydigital.domain.model.DeletedNewsEntity;
import com.applydigital.domain.model.NewsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

@Component
public class SaveFetchedNews implements ISaveFetchedNews{

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private DeletedNewsRepository deletedNewsRepository;

    public void saveAll( Mono<List<NewsEntity>> newsMono){

        Mono<List<String>> allObjectIds = Mono.fromCallable(() ->
                deletedNewsRepository.findAll()
                        .stream()
                        .map(DeletedNewsEntity::getObjectId)
                        .filter(Objects::nonNull)
                        .toList()
        );
        Mono<List<NewsEntity>> filteredNewsMono = newsMono.zipWith(allObjectIds, (newsList, ids) ->
                newsList.stream()
                        .filter(news -> !ids.contains(news.getObjectId()))
                        .toList()
        );
      newsRepository.saveAll(filteredNewsMono.block());
    }
}
