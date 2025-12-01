package application.usercases.news;


import application.usercases.NewsTestData;
import com.applydigital.application.repository.DeletedNewsRepository;
import com.applydigital.application.repository.NewsRepository;
import com.applydigital.application.usecases.news.SaveFetchedNews;
import com.applydigital.domain.model.DeletedNewsEntity;
import com.applydigital.domain.model.NewsEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


import static org.mockito.Mockito.*;

@SpringBootTest(classes = SaveFetchedNews.class)
public class SaveFetchedNewsTest {
    @MockitoBean
    private NewsRepository newsRepository;

    @MockitoBean
    private DeletedNewsRepository deletedNewsRepository;

    @Autowired
    private SaveFetchedNews subject;

    @Test
    public void shouldSaveAllNews() {
        Mono<List<NewsEntity>> monoAlgoliaNewsList = Flux.fromIterable(NewsTestData.createFakeNewsDTOList())
                .collectList();
        DeletedNewsEntity deletedNews = NewsTestData.createFakeDeletedNewsEntity();
        when(deletedNewsRepository.findAll()).thenReturn(List.of(deletedNews));
        List<NewsEntity> newsFiltered = NewsTestData.createFakeNewsDTOList().stream().filter(news -> !deletedNews.getObjectId().contains(news.getObjectId()))
                .toList();
        when(newsRepository.saveAll(newsFiltered)).thenReturn(newsFiltered);
        subject.saveAll(monoAlgoliaNewsList);
        verify(deletedNewsRepository, times(1)).findAll();
        verify(newsRepository, times(1)).saveAll(newsFiltered);
    }

}
