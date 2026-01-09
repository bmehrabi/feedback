package org.sevenp.feedback.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.sevenp.feedback.DTO.FeedbackCreateDTO;
import org.sevenp.feedback.DTO.FeedbackDTO;
import org.sevenp.feedback.exception.FeedbackNotFoundException;
import org.sevenp.feedback.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FeedbackController.class)
public class FeedbackControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FeedbackService feedbackService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnAllFeedback() throws Exception {
        FeedbackDTO dto1 = new FeedbackDTO(
                UUID.randomUUID(),
                "Message 1",
                "Alice",
                1
        );

        FeedbackDTO dto2 = new FeedbackDTO(
                UUID.randomUUID(),
                "Message 2",
                "Bob",
                2
        );

        when(feedbackService.findAll())
                .thenReturn(List.of(dto1, dto2));

        mockMvc.perform(get("/feedback"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].message").value("Message 1"))
                .andExpect(jsonPath("$[1].helpfulCount").value(2));

        verify(feedbackService).findAll();
    }

    @Test
    void shouldCreateFeedback() throws Exception {
        FeedbackCreateDTO createDTO = new FeedbackCreateDTO(
                "Great post",
                "John"
        );

        FeedbackDTO responseDTO = new FeedbackDTO(
                UUID.randomUUID(),
                "Great post",
                "John",
                0
        );

        when(feedbackService.create(any(FeedbackCreateDTO.class)))
                .thenReturn(responseDTO);

        mockMvc.perform(post("/feedback")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.message").value("Great post"))
                .andExpect(jsonPath("$.author").value("John"))
                .andExpect(jsonPath("$.helpfulCount").value(0));

        verify(feedbackService).create(any(FeedbackCreateDTO.class));
    }

    @Test
    void shouldMarkFeedbackAsHelpful() throws Exception {
        UUID id = UUID.randomUUID();

        FeedbackDTO updatedDTO = new FeedbackDTO(
                id,
                "Nice",
                "Alice",
                3
        );

        when(feedbackService.markAsHelpful(id))
                .thenReturn(updatedDTO);

        mockMvc.perform(put("/feedback/{id}/helpful", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.helpfulCount").value(3));

        verify(feedbackService).markAsHelpful(id);
    }

    @Test
    void shouldReturn404WhenFeedbackNotFound() throws Exception {
        UUID id = UUID.randomUUID();

        when(feedbackService.markAsHelpful(id))
                .thenThrow(new FeedbackNotFoundException(id));

        mockMvc.perform(put("/feedback/{id}/helpful", id))
                .andExpect(status().isNotFound());
    }

}
