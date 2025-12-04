package application.mapper;

import application.usercases.NewsTestData;
import com.applydigital.application.mapper.INewsMapper;
import com.applydigital.application.model.NewsEnum;
import com.applydigital.domain.model.NewsEntity;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;


public class NewsMapperTest {

    @Test
    void shouldMapHitToNewsEntityCorrectly() {
        // given
        Map<String, Object> hit = new HashMap<>();
        NewsEntity record = NewsTestData.createFakeNewsDTO();
        hit.put(NewsEnum.AUTHOR.key(), record.getAuthor());
        hit.put(NewsEnum.COMMENT_TEXT.key(), record.getCommentText());
        hit.put(NewsEnum.STORY_TITLE.key(), record.getStoryTitle());
        hit.put(NewsEnum.STORY_URL.key(), record.getStoryUrl());
        hit.put(NewsEnum.TAGS.key(),record.getTags());
        hit.put(NewsEnum.OBJECT_ID.key(), record.getObjectId());
        hit.put(NewsEnum.PARENT_ID.key(), record.getParentId());
        hit.put(NewsEnum.STORY_ID.key(), record.getStoryId());

        // when
        NewsEntity entity = INewsMapper.mapToNewsEntity.apply(hit);
        assertThat(entity.getCommentText(), equalTo(record.getCommentText()));
        assertThat(entity.getStoryTitle(), equalTo(record.getStoryTitle()));
        assertThat(entity.getStoryUrl(), equalTo(record.getStoryUrl()));
        assertThat(entity.getTags(), equalTo(record.getTags()));
        assertThat(entity.getObjectId(), equalTo(record.getObjectId()));
        assertThat(entity.getStoryId(), equalTo(record.getStoryId()));

        // The timestamps should not be null and should be recent

           }

    @Test
    void shouldHandleNullOrEmptyTagsGracefully() {
        // given
        Map<String, Object> hit = new HashMap<>();
        hit.put(NewsEnum.AUTHOR.key(), "Alice");
        hit.put(NewsEnum.TAGS.key(), null); // null tags

        // when
        NewsEntity entity = INewsMapper.mapToNewsEntity.apply(hit);

        // then
        assertThat(entity.getTags(),empty());
        assertThat(entity.getAuthor(), equalTo("Alice"));
    }
}
