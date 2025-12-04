package com.applydigital.application.usecases.clientalgolia;

import com.applydigital.application.model.NewsEnum;
import com.applydigital.application.repository.DeletedNewsRepository;
import com.applydigital.domain.model.NewsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


import static com.applydigital.application.mapper.INewsMapper.mapToNewsEntity;

@Component
public class AlgoliaClient implements IAlgoliaClient {

    private final WebClient webClient;

    @Value("${algolia.service.uri}")
    private String algoliaServiceUri;

    public AlgoliaClient(@Value("${algolia.service.url}") String algoliaServiceUrl, WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(algoliaServiceUrl)
                .build();
    }

    @Bean("externalServiceWebClient")
    public Mono<List<NewsEntity>> fetchData() {
        return webClient.get()
                .uri("/search_by_date?query=java")
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    List<Map<String, Object>> records = (List<Map<String, Object>>) response.get(NewsEnum.HITS.key());

                    if (records == null || records.isEmpty()) {
                        return List.<NewsEntity>of();
                    }

                    return records.stream()
                            .map(mapToNewsEntity)
                            .collect(Collectors.toList());
                });
    }



}
