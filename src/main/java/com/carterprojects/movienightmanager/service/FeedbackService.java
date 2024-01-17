package com.carterprojects.movienightmanager.service;

import com.carterprojects.movienightmanager.repository.models.Feedback;
import com.carterprojects.movienightmanager.repository.models.user.AppUser;

import java.time.LocalDateTime;
import java.util.Optional;

public interface FeedbackService {

    Optional<Feedback> getAllFeedback();

    Optional<Feedback> getAllFeedbackByUserId(Integer userId);

    Feedback saveNewFeedback(String feedbackType, String content, AppUser user, LocalDateTime submitDate);
}
