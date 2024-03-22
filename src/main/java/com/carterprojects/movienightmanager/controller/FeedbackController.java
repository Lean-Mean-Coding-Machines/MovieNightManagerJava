package com.carterprojects.movienightmanager.controller;

import com.carterprojects.movienightmanager.controller.security.Authorize;
import com.carterprojects.movienightmanager.exception.MnmAppException;
import com.carterprojects.movienightmanager.exception.ValidationException;
import com.carterprojects.movienightmanager.mapper.FeedbackMapper;
import com.carterprojects.movienightmanager.model.MnmApiResponse;
import com.carterprojects.movienightmanager.service.FeedbackService;
import com.carterprojects.movienightmanager.validators.FeedbackValidator;
import com.carterprojects.movienightmanager.model.feedback.FeedbackRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RequestBody;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/feedback")
public class FeedbackController {
    @Autowired
    FeedbackService feedbackServiceImpl;

    @Authorize
    @GetMapping("all")
    public ResponseEntity<MnmApiResponse> getAllFeedback() {
        return MnmApiResponse.success(
                feedbackServiceImpl.getAllFeedback()
                        .stream()
                        .map(FeedbackMapper::feedbackToFeedbackDto)
                        .collect(Collectors.toList()));
    }

    @Authorize
    @GetMapping("user/{userId}")
    public ResponseEntity<MnmApiResponse> getFeedbackByUserId(@PathVariable Integer userId) {
        return MnmApiResponse.success(
                feedbackServiceImpl.getAllFeedbackByUserId(userId)
                        .stream()
                        .map(FeedbackMapper::feedbackToFeedbackDto)
                        .collect(Collectors.toList()));
    }

    @Authorize
    @PostMapping("create")
    public ResponseEntity<MnmApiResponse> createFeedback(@RequestBody FeedbackRequest feedbackRequest)
            throws MnmAppException, ValidationException {
        FeedbackValidator.validateFeedback(feedbackRequest);
        var newFeedback = feedbackServiceImpl.createFeedbackFromRequest(feedbackRequest);
        if (newFeedback == null) {
            return MnmApiResponse.failed("Couldn't create feedback. Check logs for details.");
        }
        return MnmApiResponse.created(FeedbackMapper.feedbackToFeedbackDto(newFeedback));
    }
}
