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

    public Feedback getFeedbackById(int id) {
        return feedbackRepository.getFeedbackById(id);
    }

    public ArrayList<Feedback> getAllFeedback() {
        return feedbackRepository.getAllFeedback();

    }

    public Feedback createFeedback(Feedback feedback) {
      return feedbackRepository.createFeedback(feedback);
    }

    public Feedback updateFeedbackById(Feedback feedback) {
      return  feedbackRepository.updateFeedback(feedback);
    }

    public Feedback deleteFeedback(Feedback feedback) {
       return feedbackRepository.deleteFeedback(feedback);
    }
}
