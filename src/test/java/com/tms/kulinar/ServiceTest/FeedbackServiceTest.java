package com.tms.kulinar.ServiceTest;

import com.tms.kulinar.domain.Feedback;
import com.tms.kulinar.repository.FeedbackRepository;
import com.tms.kulinar.service.FeedbackService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class FeedbackServiceTest {

    @Mock
    private FeedbackRepository feedbackRepository;

    private FeedbackService feedbackService;

    private Feedback feedback;

    private List<Feedback> feedbacks;

    @BeforeEach
    void setFeedback() {
        MockitoAnnotations.openMocks(this);
        feedbackService = new FeedbackService(feedbackRepository);
        feedback = new Feedback(1,"tectName","testContent",1);
        feedbacks = new ArrayList<>();
        feedbacks.add(feedback);
        feedbackRepository.createFeedback(feedback);
    }


    @Test
    void getFeedbackById() {
        when(feedbackRepository.getFeedbackById(feedback.getId())).thenReturn(feedback);
        Optional<Feedback> optionalArticle = Optional.ofNullable(feedbackService.getFeedbackById(feedback.getId()));
        assertTrue(optionalArticle.isPresent());
        verify(feedbackRepository, times(1)).getFeedbackById(feedback.getId());
    }

    @Test
    void getAllFeedback() {
        when(feedbackRepository.getAllFeedback()).thenReturn((ArrayList<Feedback>) feedbacks);
        Optional<List<Feedback>> optionalBooks = Optional.ofNullable(feedbackService.getAllFeedback());

        assertTrue(optionalBooks.isPresent());
        verify(feedbackRepository, times(1)).getAllFeedback();
    }

    @Test
    void createFeedback() {
        Feedback newFeedback = new Feedback(1,"tectName","testContent",1);
        feedbackService.createFeedback(newFeedback);
        verify(feedbackRepository, times(2)).createFeedback(ArgumentMatchers.any(Feedback.class));
    }
}
