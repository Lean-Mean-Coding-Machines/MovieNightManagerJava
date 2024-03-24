package com.carterprojects.movienightmanager.repository;

import com.carterprojects.movienightmanager.repository.models.feedback.Feedback;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends CrudRepository<Feedback, Integer> {
    List<Feedback> findAll();

    List<Feedback> findAllFeedbackByUser_Id(Integer userId);
}
