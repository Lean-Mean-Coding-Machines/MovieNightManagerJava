package com.carterprojects.movienightmanager.service;

import com.carterprojects.movienightmanager.repository.models.Feedback;
import com.carterprojects.movienightmanager.repository.FeedbackRepository;
import com.carterprojects.movienightmanager.repository.models.user.AppUser;
import com.carterprojects.movienightmanager.exception.MnmAppException;
import com.carterprojects.movienightmanager.model.feedback.FeedbackRequest;
import com.carterprojects.movienightmanager.repository.AppUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    FeedbackRepository feedbackRepository;
    @Autowired
    AppUserRepository appUserRepository;

    public Optional<Feedback> getAllFeedback() {
        return feedbackRepository.findAllFeedback();
    }

    public Optional<Feedback> getAllFeedbackByUserId(Integer userId) {
        return feedbackRepository.findAllFeedbackByUser_Id(userId);
    }

    // Need to add date time
    public Feedback createFeedbackFromRequest(FeedbackRequest feedbackRequest) throws MnmAppException {
        var user = appUserRepository.findById(feedbackRequest.getUserId())
                .orElseThrow(
                        () -> {
                            log.error("Could not create feedback because user with id: {} was not found",
                                    feedbackRequest.getUserId());
                            return new MnmAppException("Could not create feedback because the user was not found");
                        });

        return saveNewFeedback(feedbackRequest, user);
    }

    // Need to add date time
    public Feedback saveNewFeedback(FeedbackRequest feedbackRequest, AppUser user) {
        var newFeedback = Feedback.builder()
                .feedbackType(feedbackRequest.getFeedbackType())
                .content(feedbackRequest.getContent())
                .user(user)
                // .submitDate(submitDate)
                .build();
        return feedbackRepository.save(newFeedback);
    }
}
