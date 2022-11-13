package com.carterprojects.movienightmanager.repository;

import com.carterprojects.movienightmanager.repository.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    Optional<AppUser> findAppUserByEmailOrUsername(String email, String username);
}