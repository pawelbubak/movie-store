package com.take.store.api.movie;

import com.take.store.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.web.servlet.MockMvc;

import static com.take.store.utils.PageableAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MovieController.class)
class MovieControllerTest {
    @MockBean
    private MovieRepository movieRepository;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void evaluatesPageable() throws Exception {
        mockMvc.perform(get("/movies")
                .param("page", "1")
                .param("size", "10")
                .param("sort", "id,desc")
                .param("sort", "title,asc"))
                .andExpect(status().isOk());

        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);
        verify(movieRepository).findAll(pageableCaptor.capture());
        PageRequest pageable = (PageRequest) pageableCaptor.getValue();

        assertThat(pageable).hasPageNumber(1);
        assertThat(pageable).hasPageSize(10);
        assertThat(pageable).hasSort("id", Sort.Direction.DESC);
        assertThat(pageable).hasSort("title", Sort.Direction.ASC);
    }

    @Test
    void evaluatesPageableDefault() throws Exception {
        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk());

        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);
        verify(movieRepository).findAll(pageableCaptor.capture());
        PageRequest pageable = (PageRequest) pageableCaptor.getValue();

        assertThat(pageable).hasPageNumber(0);
        assertThat(pageable).hasPageSize(20);
    }

}
