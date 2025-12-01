package presentation;

import com.applydigital.application.model.NewsGetResponseDTO;
import com.applydigital.application.usecases.news.DeleteNewsService;
import com.applydigital.application.usecases.news.GetNewsService;
import com.applydigital.presentation.controller.NewsController;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = NewsController.class)
public class NewsControllerTest {


    @MockitoBean
    private GetNewsService newsService;

    @MockitoBean
    private DeleteNewsService deleteNewsService;

    @Autowired
    private NewsController subject;

    public void setup(){}


    @Test
    public void getAllNewsShouldResponseOK() {

        when(newsService.findNewsByComplexCriteria(any(), any(),any(),any())).thenReturn(List.of());
        ResponseEntity<List<NewsGetResponseDTO>> entity = subject.getAllNews(any(), any(),any(),any());
        verify(newsService, times(1)).findNewsByComplexCriteria(any(), any(),any(),any());
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }


    @Test
    public void getNewsByIdShouldResponseOK() {

        when(newsService.getNewsByObjectId("objectID")).thenReturn(any());
        ResponseEntity<NewsGetResponseDTO> entity = subject.getNewsByObjectId("objectID");
        verify(newsService, times(1)).getNewsByObjectId(any());
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }



    @Test
    public void deleteNewsByIdShouldResponseOK() {
        Mockito.doNothing().when(deleteNewsService).deleteNewsById("objectID");
        ResponseEntity<Void> entity = subject.deleteNewsById("objectID");
        verify(deleteNewsService, times(1)).deleteNewsById("objectID");
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }






}
