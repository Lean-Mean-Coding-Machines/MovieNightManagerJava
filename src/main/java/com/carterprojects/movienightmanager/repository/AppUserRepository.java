package com.carterprojects.movienightmanager.repository;

import com.carterprojects.movienightmanager.repository.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    Optional<AppUser> findAppUserByEmailOrUsername(String email, String username);
}