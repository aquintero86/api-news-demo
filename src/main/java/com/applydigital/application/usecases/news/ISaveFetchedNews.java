package com.applydigital.application.usecases.news;

import com.applydigital.domain.model.NewsEntity;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

public interface ISaveFetchedNews {

    void saveAll( Mono<List<NewsEntity>> newsList);
}