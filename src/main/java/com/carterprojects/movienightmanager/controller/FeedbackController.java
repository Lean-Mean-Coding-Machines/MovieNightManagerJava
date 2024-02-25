package com.carterprojects.movienightmanager.controller;

import com.carterprojects.movienightmanager.controller.security.Authorize;
import com.carterprojects.movienightmanager.mapper.FeedbackMapper;
import com.carterprojects.movienightmanager.model.MnmApiResponse;
import com.carterprojects.movienightmanager.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/feedback")
public class FeedbackController {
    @Autowired
    FeedbackService FeedbackServiceImpl;

    @Authorize
    @GetMapping("all")
    public ResponseEntity<MnmApiResponse> getAllFeedback() {
        return MnmApiResponse.success(
                FeedbackServiceImpl.getAllFeedback()
                        .stream()
                        .map(FeedbackMapper::feedbackToFeedbackDto)
                        .collect(Collectors.toList()));
    }

    @Authorize
    @GetMapping("user/{userId}")
    public ResponseEntity<MnmApiResponse> getFeedbackByUserId(@PathVariable Integer userId) {
        return MnmApiResponse.success(
                FeedbackServiceImpl.getAllFeedbackByUserId(userId)
                        .stream()
                        .map(FeedbackMapper::feedbackToFeedbackDto)
                        .collect(Collectors.toList()));
    }

    @Authorize
    @PostMapping("create")
    public ResponseEntity<MnmApiResponse> createFeedback(@RequestBody )
}
