package com.tms.kulinar;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.tms.kulinar.controller.FeedbackController;
import com.tms.kulinar.domain.Feedback;
import com.tms.kulinar.domain.User;
import com.tms.kulinar.domain.request.RegistrationUser;
import com.tms.kulinar.service.FeedbackService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class FeedbackControllerTest {
    MockMvc mvc;

    @Mock
    FeedbackService feedbackService;

    @InjectMocks
    FeedbackController feedbackController;

    private final Feedback feedback = new Feedback();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mvc = MockMvcBuilders.standaloneSetup(feedbackController).build();
        feedback.setName("TestMain");
        feedback.setContent("TestContent");

    }

    @Test
    void findAllFeedback() throws Exception {
        ArrayList<Feedback> list = new ArrayList<>();
        list.add(new Feedback());
        when(feedbackService.getAllFeedback()).thenReturn(list);

        MvcResult result = mvc.perform(get("/feedback/getAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andReturn();
        assertThat(result.getResponse().getContentAsString(), allOf(notNullValue()));
        verify(feedbackService, times(1)).getAllFeedback();
    }

    @Test
    void findFeedbackById() throws Exception {
        when(feedbackService.getFeedbackById(anyInt())).thenReturn(feedback);

        MvcResult result = mvc.perform(get("/feedback/{id}", anyInt()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andReturn();
        assertThat(result.getResponse().getContentAsString(), allOf(notNullValue()));
        verify(feedbackService, times(1)).getFeedbackById(anyInt());
    }

    @Test
    void createFeedback() throws Exception {
        when(feedbackService.createFeedback(any(Feedback.class))).thenReturn(feedback);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(new RegistrationUser());

        MvcResult result = mvc.perform(post("/feedback/create").contentType(APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isCreated())
                .andReturn();
        assertThat(result.getResponse().getContentAsString(), allOf(notNullValue()));
        verify(feedbackService, times(1)).createFeedback(any());
    }

    @Test
    void updateFeedback() throws Exception {
        when(feedbackService.updateFeedbackById(any(Feedback.class))).thenReturn(feedback);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(new User());

        MvcResult result = mvc.perform(put("/feedback/update/{id}").contentType(APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andReturn();
        assertThat(result.getResponse().getContentAsString(), allOf(notNullValue()));
        verify(feedbackService, times(1)).updateFeedbackById(any(Feedback.class));
    }

    @Test
    void deleteFeedback() throws Exception {
        MvcResult result = mvc.perform(delete("/feedback/{id}", anyLong()))
                .andExpect(status().isNoContent())
                .andReturn();
        verify(feedbackService, times(1)).deleteFeedback(any());
    }
}
