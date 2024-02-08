package com.carterprojects.movienightmanager.repository;

import com.carterprojects.movienightmanager.repository.models.Community;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityRepository extends CrudRepository<Community, Integer> {
}