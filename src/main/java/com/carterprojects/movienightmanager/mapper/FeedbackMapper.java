package com.carterprojects.movienightmanager.mapper;

import com.carterprojects.movienightmanager.model.feedback.FeedbackDto;
import com.carterprojects.movienightmanager.repository.models.feedback.Feedback;

// import java.util.stream.Collectors;

public class FeedbackMapper {

    public static FeedbackDto feedbackToFeedbackDto(Feedback feedback) {
        return FeedbackDto.builder()
                .id(feedback.getId())
                .userId(feedback.getUser().getId())
                .submitDate(feedback.getSubmitDate())
                .content(feedback.getContent())
                .feedbackType(feedback.getFeedbackType())
                .build();
    }

}
