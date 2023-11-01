package com.carterprojects.movienightmanager.repository;

import com.carterprojects.movienightmanager.repository.models.Nomination;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NominationRepository extends CrudRepository<Nomination, Integer> {
    List<Nomination> findAllByMovieNightSegment_Id(Integer movieSegmentId);
    List<Nomination> findAllByUser_Id(Integer userId);
    Optional<Nomination> findByUser_IdAndIdAndMovieNightSegment_Id(Integer userId, Integer id, Integer segmentId);
}