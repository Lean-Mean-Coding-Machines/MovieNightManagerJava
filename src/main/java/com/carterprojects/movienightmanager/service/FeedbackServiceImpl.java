package com.carterprojects.movienightmanager.service;

import com.carterprojects.movienightmanager.repository.models.Feedback;
import com.carterprojects.movienightmanager.repository.FeedbackRepository;
import com.carterprojects.movienightmanager.repository.models.user.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    FeedbackRepository feedbackRepository;

    public Optional<Feedback> getAllFeedback() {
        return feedbackRepository.findAllFeedback();
    }

    public Optional<Feedback> getAllFeedbackByUserId(Integer userId) {
        return feedbackRepository.findAllFeedbackByUser_Id(userId);
    }

    public Feedback saveNewFeedback(String feedbackType, String content, AppUser user, LocalDateTime submitDate) {
        var newFeedback = Feedback.builder()
                .feedbackType(feedbackType)
                .content(content)
                .user(user)
                .submitDate(submitDate)
                .build();
        return feedbackRepository.save(newFeedback);
    }
}
