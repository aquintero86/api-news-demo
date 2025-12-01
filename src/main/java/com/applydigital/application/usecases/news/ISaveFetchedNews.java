package com.applydigital.application.usecases.news;

import com.applydigital.domain.model.NewsEntity;

import java.util.List;

public interface ISaveFetchedNewsUseCase {

    void execute(List<NewsEntity> newsList);
}