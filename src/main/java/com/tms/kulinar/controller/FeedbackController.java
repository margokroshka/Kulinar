package com.tms.kulinar.controller;

import com.tms.kulinar.domain.Feedback;
import com.tms.kulinar.exception.CustomException;
import com.tms.kulinar.exception.ResourceNotFoundException;
import com.tms.kulinar.service.FeedbackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final FeedbackService feedbackService;

    @Autowired
    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable int id) {
        Feedback feedback = feedbackService.getFeedbackById(id);
        return new ResponseEntity<>(feedback, feedback.getId() != 0 ? HttpStatus.OK : HttpStatus.CONFLICT);
    }

    @GetMapping("/getAll")
    public ResponseEntity<ArrayList<Feedback>> getAllFeedback() {
        if (feedbackService.getAllFeedback().isEmpty()) {
            throw new ResourceNotFoundException("Not found any feedback");
        }
        return new ResponseEntity<>(feedbackService.getAllFeedback(), HttpStatus.OK);
    }

    @PostMapping("/createFeedback")
    public ResponseEntity<Feedback> createFeedback(@RequestBody @Valid Feedback feedback, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            for (ObjectError o : bindingResult.getAllErrors()) {
                log.warn(o.getDefaultMessage());
            }
            throw new CustomException("FEEDBACK_WAS_NOT_CREATED");
        }
        return new ResponseEntity<>(feedbackService.createFeedback(feedback) != null ? HttpStatus.CREATED : HttpStatus.CONFLICT);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HttpStatus> updateFeedback(@RequestBody @Valid Feedback feedback, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            for (ObjectError o : bindingResult.getAllErrors()) {
                log.warn(o.getDefaultMessage());
            }
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        feedbackService.updateFeedback(feedback);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> deleteFeedback(@RequestBody @Valid Feedback feedback) {
        Feedback resultFeedback = feedbackService.deleteFeedback(feedback);
        if (resultFeedback == null) {
            throw new CustomException("FEEDBACK_WAS_NOT_DELETED");
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}



