package com.carterprojects.movienightmanager.repository;

import com.carterprojects.movienightmanager.repository.models.Feedback;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FeedbackRepository extends CrudRepository<Feedback, Integer> {
    Optional<Feedback> getFeedback();
}
