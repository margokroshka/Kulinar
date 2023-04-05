package com.tms.kulinar.service;


import com.tms.kulinar.domain.Feedback;
import com.tms.kulinar.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FeedbackService {
    public FeedbackRepository feedbackRepository;
    @Autowired
    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public Feedback getFeedbackByName(String name) {
        return feedbackRepository.getFeedbackByName(name);
    }

    public ArrayList<Feedback> getAllFeedback() {
        return feedbackRepository.getAllFeedback();

    }

    public void createFeedback(Feedback feedback) {
       feedbackRepository.createFeedback(feedback);
    }

    public void updateFeedbackById(Feedback feedback) {
        feedbackRepository.updateFeedback(feedback);
    }

    public void deleteFeedback(Feedback feedback) {
        feedbackRepository.deleteFeedback(feedback);
    }
}
