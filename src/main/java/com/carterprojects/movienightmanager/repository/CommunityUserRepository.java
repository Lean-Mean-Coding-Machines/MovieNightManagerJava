package com.carterprojects.movienightmanager.repository;

import com.carterprojects.movienightmanager.repository.models.CommunityUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityUserRepository extends CrudRepository<CommunityUser, Integer> {
}