package org.dm.trainingcourses.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.dm.trainingcourses.model.Training;
import org.dm.trainingcourses.model.TrainingFactory;
import org.dm.trainingcourses.service.TrainingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TrainingControllerMockTest {

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TrainingService trainingService;

    @Test
    void shouldCreateTraining() throws Exception {
        Training training = TrainingFactory.createTraining();
        training.setId(1L);
        when(trainingService.createTraining(any())).thenReturn(training);
        this.mockMvc.perform(
                post(TrainingController.PATH_TRAININGS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(training)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(training.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(training.getDescription()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(training.getPrice()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.speaker").value(training.getSpeaker()));
    }

    @Test
    void shouldReturnTraining() throws Exception {
        Training training = TrainingFactory.createTraining();
        when(trainingService.getById(anyLong())).thenReturn(Optional.of(training));
        this.mockMvc.perform(
                get(TrainingController.PATH_TRAININGS + "/123" ))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(training.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(training.getDescription()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(training.getPrice()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.speaker").value(training.getSpeaker()));

    }

    private String asJsonString(Object object) throws Exception {
        return objectMapper.writeValueAsString(object);
    }

}
