package com.carterprojects.movienightmanager.repository;

import com.carterprojects.movienightmanager.repository.models.MovieNightSegment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MovieNightSegmentRepository extends CrudRepository<MovieNightSegment, Integer> {
    @Query("select seg from MovieNightSegment seg where seg.nominationStartDate <= :date and seg.nominationLockDate >= :date and seg.community.id = :communityId")
    Optional<MovieNightSegment> getMovieNightSegmentByDateAndCommunityId(@Param("date") LocalDateTime date, @Param("communityId") Integer communityId);

    @Query("select seg from MovieNightSegment seg where seg.nominationStartDate <= :date and seg.nominationLockDate >= :date and seg.id = :segmentId")
    Optional<MovieNightSegment> getCurrentMovieNightSegmentById(@Param("date") LocalDateTime date, @Param("segmentId") Integer segmentId);

    @Query("select seg from MovieNightSegment seg where seg.id >= :lowest_id and seg.id < :current_id")
    List<MovieNightSegment> getPreviousMovieNightSegments(@Param("lowest_id") Integer lowest_id,
                                                          @Param("current_id") Integer current_id);
}
