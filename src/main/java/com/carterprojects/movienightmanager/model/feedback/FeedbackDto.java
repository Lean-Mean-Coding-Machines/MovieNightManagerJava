package com.carterprojects.movienightmanager.model.feedback;

import com.carterprojects.movienightmanager.repository.models.feedback.FeedbackType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackDto {
    Integer id;
    Integer userId;
    LocalDateTime submitDate;
    FeedbackType feedbackType;
    String content;
}
