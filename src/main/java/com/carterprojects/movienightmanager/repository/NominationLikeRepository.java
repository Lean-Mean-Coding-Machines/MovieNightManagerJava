package com.carterprojects.movienightmanager.repository;

import com.carterprojects.movienightmanager.repository.models.NominationLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface NominationLikeRepository extends JpaRepository<NominationLike, Integer> {
    List<NominationLike> findNominationLikesByUser_Id(Integer userId);
    List<NominationLike> findNominationLikesByNomination_Id(Integer nominatonId);
    Optional<NominationLike> findByNomination_IdAndUser_Id(Integer nominatonId, Integer userId);
}