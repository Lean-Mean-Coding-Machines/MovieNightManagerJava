package com.carterprojects.movienightmanager.model.feedback;

import lombok.Getter;

@Getter
public class FeedbackRequest {
    Integer userId;
    // Auto generated on submit?
    // String submitDate;
    String feedbackType;
    String content;
}
