package application.usercases.news;


import application.usercases.NewsTestData;
import com.applydigital.application.exception.NewsNotFoundException;
import com.applydigital.application.model.NewsGetResponseDTO;
import com.applydigital.application.repository.NewsRepository;
import com.applydigital.application.usecases.news.GetNewsService;
import com.applydigital.domain.model.NewsEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.YearMonth;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = GetNewsService.class)
public class GetNewsServiceTest {


    @MockitoBean
    private NewsRepository newsRepository;

    @MockitoBean
    private MongoTemplate mongoTemplate;



    @Autowired
    private GetNewsService subject;


    @Test
    public void getAllNewsShouldResponseOk() {
        List<NewsEntity> queryResponse = List.of(NewsTestData.createFakeNewsDTO());
        Query query = new Query();
        when(mongoTemplate.find(query,NewsEntity.class)).thenReturn(queryResponse);
        List<NewsGetResponseDTO> response = subject.findNewsByComplexCriteria(null,null, null, null);
        verify(mongoTemplate, times(1)).find(query,NewsEntity.class);
        assertEquals(queryResponse.get(0).getAuthor(), response.get(0).getAuthor());
    }



    @Test
    public void getAllNewsShouldThrowException() {
        Query query = new Query();
        when(mongoTemplate.find(query,NewsEntity.class)).thenReturn(null);
        Assertions.assertThrows(NewsNotFoundException.class, () -> {
            subject.findNewsByComplexCriteria(null,null, null, null);
        });
        verify(mongoTemplate, times(1)).find(query,NewsEntity.class);
    }
    @Test
    public void getNewsByIdShouldResponseSuccesfull() {
        NewsEntity queryResponse = NewsTestData.createFakeNewsDTO();
        when(newsRepository.findByObjectId(queryResponse.getObjectId())).thenReturn(queryResponse);
        NewsGetResponseDTO response = subject.getNewsByObjectId(queryResponse.getObjectId());
        verify(newsRepository, times(1)).findByObjectId(any());
        assertEquals(queryResponse.getAuthor(), response.getAuthor());
    }


    @Test
    public void getNewsShouldResponseOkFilterByAuthor() {
        List<NewsEntity> queryResponse = List.of(NewsTestData.createFakeNewsDTO());
        Query query = new Query();
        query.addCriteria(Criteria.where("author").is(NewsTestData.createFakeNewsDTO().getAuthor()));
        when(mongoTemplate.find(query,NewsEntity.class)).thenReturn(queryResponse);
        List<NewsGetResponseDTO> response = subject.findNewsByComplexCriteria(NewsTestData.createFakeNewsDTO().getAuthor(),null, null, null);
        verify(mongoTemplate, times(1)).find(query,NewsEntity.class);
        assertEquals(queryResponse.get(0).getAuthor(), response.get(0).getAuthor());
    }


    @Test
    public void getNewsShouldResponseOkFilterByTitleAndMonth() {
        List<NewsEntity> queryResponse = List.of(NewsTestData.createFakeNewsDTO());
        Month month = Month.OCTOBER;
        int year = 2025;
        Query query = new Query();
        query.addCriteria(Criteria.where("storyTitle").regex(NewsTestData.createFakeNewsDTO().getStoryTitle(), "i"));
        String dateStr = month.getValue()< 10 ?  String.format("%d-0%d",  year, month.getValue()) : String.format("%d-%d",  year, month.getValue());
        YearMonth ym = YearMonth.parse(dateStr);
        LocalDateTime start = ym.atDay(1).atStartOfDay();
        LocalDateTime end = ym.atEndOfMonth().atTime(LocalTime.MAX);
        query.addCriteria(Criteria.where("createdAt").gte(start).lte(end));
         when(mongoTemplate.find(query,NewsEntity.class)).thenReturn(queryResponse);
        List<NewsGetResponseDTO> response = subject.findNewsByComplexCriteria(null,null, NewsTestData.createFakeNewsDTO().getStoryTitle(), Month.OCTOBER);
        verify(mongoTemplate, times(1)).find(query,NewsEntity.class);
        assertEquals(NewsTestData.createFakeNewsDTO().getStoryTitle(), response.get(0).getStoryTitle());
    }


    @Test
    public void getNewsByIdShouldResponseThrowException() {
        NewsEntity queryResponse = NewsTestData.createFakeNewsDTO();
        when(newsRepository.findByObjectId("object_id")).thenReturn(null);
        Assertions.assertThrows(NewsNotFoundException.class, () -> {
            subject.getNewsByObjectId("object_id");
        });
        verify(newsRepository, times(1)).findByObjectId("object_id");
    }


}
