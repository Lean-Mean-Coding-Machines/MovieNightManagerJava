package com.carterprojects.movienightmanager.repository.models;

import com.carterprojects.movienightmanager.repository.models.user.AppUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "feedback")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "app_user_id", referencedColumnName = "user_id", nullable = false)
    AppUser user;

    LocalDateTime submitDate;

    String feedbackType;

    String content;
}
