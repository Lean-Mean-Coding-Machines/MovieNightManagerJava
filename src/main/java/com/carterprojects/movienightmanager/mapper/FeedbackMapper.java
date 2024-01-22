package com.carterprojects.movienightmanager.mapper;

import com.carterprojects.movienightmanager.model.dto.FeedbackDto;
import com.carterprojects.movienightmanager.repository.models.Feedback;

import java.util.stream.Collectors;

public class FeedbackMapper {

    public static FeedbackDto feedbackToDto(Feedback feedback) {
        return FeedbackDto.builder()
            .id(feedback.getId())
            .user(feedback.getUser())
            .submitDate(feedback.getSubmitDate())
            .content(feedback.getContent())
            .feedbackType(feedback.getFeedbackType())
            .build();
    }

}
