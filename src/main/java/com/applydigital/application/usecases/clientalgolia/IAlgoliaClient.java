package com.applydigital.application.usecases.clientalgolia;

import com.applydigital.domain.model.AlgoliaResponse;
import com.applydigital.domain.model.NewsEntity;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

public interface IAlgoliaClient {

    public Mono<List<NewsEntity>>  fetchData();
}
