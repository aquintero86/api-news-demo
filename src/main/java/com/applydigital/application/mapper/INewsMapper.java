package com.applydigital.application.mapper;


import com.applydigital.application.model.NewsEnum;
import com.applydigital.domain.model.NewsEntity;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface INewsMapper extends Function<Map<String, Object>, List<NewsEntity>> {

    final Function<Map<String, Object>, NewsEntity> mapToNewsEntity = hit -> {
        NewsEntity entity = new NewsEntity();
        entity.setAuthor((String) hit.get(NewsEnum.AUTHOR.key()));
        entity.setCommentText((String) hit.get(NewsEnum.COMMENT_TEXT.key()));
        entity.setStoryTitle((String) hit.get(NewsEnum.STORY_TITLE.key()));
        entity.setStoryUrl((String) hit.get(NewsEnum.STORY_URL.key()));

        Object tagsObj = hit.get(NewsEnum.TAGS.key());
        entity.setTags(tagsObj instanceof List<?>
                ? ((List<?>) tagsObj).stream().map(Object::toString).collect(Collectors.toList())
                : Collections.emptyList());

        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        entity.setObjectId((String) hit.get(NewsEnum.OBJECT_ID.key()));
        entity.setParentId(hit.get(NewsEnum.PARENT_ID.key()) != null
                ? ((Number) hit.get(NewsEnum.PARENT_ID.key())).longValue()
                : null);
        entity.setStoryId(hit.get(NewsEnum.STORY_ID.key()) != null
                ? ((Number) hit.get(NewsEnum.STORY_ID.key())).longValue()
                : null);

        return entity;
    };
}