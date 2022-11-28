package com.carterprojects.movienightmanager.repository;

import com.carterprojects.movienightmanager.repository.models.MovieNightSegment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface MovieNightSegmentRepository extends CrudRepository<MovieNightSegment, Integer> {
    @Query("select seg from MovieNightSegment seg where seg.nominationStartDate <= :date and seg.nominationLockDate >= :date")
    Optional<MovieNightSegment> getMovieNightSegmentByDate(@Param("date") LocalDateTime date);
}
