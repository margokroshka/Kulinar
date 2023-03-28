package com.tms.kulinar.controller;

import com.tms.kulinar.domain.Feedback;
import com.tms.kulinar.exception.FeedbackNotFoundException;
import com.tms.kulinar.repository.FeedbackRepository;
import com.tms.kulinar.service.FeedbackService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final FeedbackRepository feedbackRepository;

    @Autowired
    public FeedbackController(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Operation(description = "Ищет фидбэк по name", summary = "Ищет фидбэк")
    @ApiResponses(value={
            @ApiResponse(responseCode = "200", description = "Все ОК! Улыбаемся и машем"),
            @ApiResponse(responseCode = "404", description = "Куда ты жмал?!!? Ничего не нашел")
    })
    @GetMapping("/{name}")
    @Tag(name = "byName", description = "ищём по name")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable String name)  {
        Feedback feedback = feedbackRepository.getFeedbackByName(name);
      return  new ResponseEntity<>(feedback, feedback.getId()!=0?HttpStatus.OK: HttpStatus.CONFLICT);
    }

    @GetMapping
    public ResponseEntity<ArrayList<Feedback>> getAllFeedback() {
        return new ResponseEntity<>(feedbackRepository.getAllFeedback(), HttpStatus.OK);
    }

    @PostMapping("/createFeedback")
    public ResponseEntity<HttpStatus> createFeedback(@RequestBody @Valid Feedback feedback) {
        feedbackRepository.createFeedback(feedback);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<HttpStatus> updateFeedback(@RequestBody @Valid Feedback feedback, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            for (ObjectError o : bindingResult.getAllErrors()) {
                log.warn(o.getDefaultMessage());
            }
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        feedbackRepository.updateFeedback(feedback);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteFeedback(@RequestBody @Valid Feedback feedback, BindingResult bindingResult) {
        feedbackRepository.deleteFeedback(feedback);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}



