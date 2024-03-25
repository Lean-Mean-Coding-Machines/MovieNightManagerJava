package com.carterprojects.movienightmanager.model.feedback;

import com.carterprojects.movienightmanager.repository.models.feedback.FeedbackType;
import lombok.Getter;

@Getter
public class FeedbackRequest {
    Integer userId;
    FeedbackType feedbackType;
    String content;
}
