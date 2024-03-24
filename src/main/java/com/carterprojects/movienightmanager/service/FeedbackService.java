package com.carterprojects.movienightmanager.service;

import com.carterprojects.movienightmanager.exception.MnmAppException;
import com.carterprojects.movienightmanager.model.feedback.FeedbackRequest;
import com.carterprojects.movienightmanager.repository.models.feedback.Feedback;
import com.carterprojects.movienightmanager.repository.models.user.AppUser;

import java.util.List;

public interface FeedbackService {

    List<Feedback> getAllFeedback();

    List<Feedback> getAllFeedbackByUserId(Integer userId);

    Feedback createFeedbackFromRequest(FeedbackRequest feedbackRequest) throws MnmAppException;

    Feedback saveNewFeedback(FeedbackRequest feedbackRequest, AppUser user);
}
