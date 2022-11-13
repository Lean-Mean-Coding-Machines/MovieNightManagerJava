package com.carterprojects.movienightmanager.repository;

import com.carterprojects.movienightmanager.repository.models.Nomination;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NominationRepository extends CrudRepository<Nomination, Integer> {
    List<Nomination> findAllByMovieNightSegment_Id(Integer movieSegmentId);
    List<Nomination> findAllByUser_Id(Integer userId);
}