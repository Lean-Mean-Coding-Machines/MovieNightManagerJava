package com.carterprojects.movienightmanager.service;

import com.carterprojects.movienightmanager.exception.MnmAppException;
import com.carterprojects.movienightmanager.model.feedback.FeedbackRequest;
import com.carterprojects.movienightmanager.repository.FeedbackRepository;
import com.carterprojects.movienightmanager.repository.models.Feedback;
import com.carterprojects.movienightmanager.repository.models.user.AppUser;

import java.time.LocalDateTime;
import java.util.Optional;

public interface FeedbackService {

    Optional<Feedback> getAllFeedback();

    Optional<Feedback> getAllFeedbackByUserId(Integer userId);

    Feedback createFeedbackFromRequest(FeedbackRequest feedbackRequest) throws MnmAppException;

    Feedback saveNewFeedback(FeedbackRequest feedbackRequest, AppUser user);
}
