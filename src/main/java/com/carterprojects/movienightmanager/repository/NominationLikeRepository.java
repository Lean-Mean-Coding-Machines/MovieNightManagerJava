package com.carterprojects.movienightmanager.repository;

import com.carterprojects.movienightmanager.repository.models.NominationLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NominationLikeRepository extends JpaRepository<NominationLike, Integer> {
    List<NominationLike> findNominationLikesByUser_Id(Integer userId);
    List<NominationLike> findNominationLikesByNomination_Id(Integer nominatonId);
}