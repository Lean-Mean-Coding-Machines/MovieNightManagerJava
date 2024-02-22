package com.carterprojects.movienightmanager.repository;

import com.carterprojects.movienightmanager.repository.models.CommunityUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityUserRepository extends CrudRepository<CommunityUser, Integer> {
    List<CommunityUser> findCommunityUserByUser_Id(Integer userId);
}