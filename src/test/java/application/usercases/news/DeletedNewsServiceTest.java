package application.usercases;


import com.applydigital.application.exception.NewsNotFoundException;
import com.applydigital.application.repository.DeletedNewsRepository;
import com.applydigital.application.repository.NewsRepository;
import com.applydigital.application.usecases.news.DeleteNewsService;
import com.applydigital.domain.model.DeletedNewsEntity;
import com.applydigital.domain.model.NewsEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest(classes = DeleteNewsService.class)
public class DeletedNewsServiceTest {
    @MockitoBean
    private NewsRepository newsRepository;

    @MockitoBean
    private DeletedNewsRepository deletedNewsRepository;


    @Autowired
    private DeleteNewsService subject;


    @Test
    public void deletedNewsShouldResponseOk() {
        NewsEntity queryResponse = NewsTestData.createFakeNewsDTO();
        long resultDeleted = 1;
        when(newsRepository.findByObjectId(queryResponse.getObjectId())).thenReturn(queryResponse);
        when(newsRepository.deleteByObjectId(queryResponse.getObjectId())).thenReturn(resultDeleted);
        LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.HOURS);
        DeletedNewsEntity deletedNews = new DeletedNewsEntity(queryResponse.getObjectId(), now);
        when(deletedNewsRepository.save(deletedNews)).thenReturn(deletedNews);
        subject.deleteNewsById(queryResponse.getObjectId());
        verify(newsRepository, times(1)).findByObjectId(queryResponse.getObjectId());
        verify(newsRepository, times(1)).deleteByObjectId(queryResponse.getObjectId());
        verify(deletedNewsRepository, times(1)).save(deletedNews);
    }



    @Test
    public void deletedNewsShouldThrowException() {
        when(newsRepository.findByObjectId("object_id")).thenReturn(null);
        Assertions.assertThrows(NewsNotFoundException.class, () -> {
            subject.deleteNewsById("object_id");
        });
        verify(newsRepository, times(1)).findByObjectId("object_id");
    }



}
