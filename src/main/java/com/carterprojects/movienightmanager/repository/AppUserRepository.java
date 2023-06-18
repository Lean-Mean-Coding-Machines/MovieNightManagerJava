package com.carterprojects.movienightmanager.repository;

import com.carterprojects.movienightmanager.repository.models.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    Optional<AppUser> findAppUserByEmailOrUsername(String email, String username);
    Optional<UserDetails> findAppUserDetailsByUsername(String username);
    Optional<AppUser> findAppUserByUsername(String username);
    Optional<AppUser> findAppUserByUsernameAndPassword(String username, String password);
}