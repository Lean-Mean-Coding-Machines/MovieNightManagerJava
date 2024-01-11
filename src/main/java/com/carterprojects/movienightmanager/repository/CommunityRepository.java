package com.carterprojects.movienightmanager.repository;

import com.carterprojects.movienightmanager.repository.models.Community;
import com.carterprojects.movienightmanager.repository.models.Nomination;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommunityRepository extends CrudRepository<Community, Integer> {
}