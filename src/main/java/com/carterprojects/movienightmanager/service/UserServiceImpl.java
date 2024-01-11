package com.carterprojects.movienightmanager.service;

import com.carterprojects.movienightmanager.exception.MnmAppException;
import com.carterprojects.movienightmanager.model.user.UserCreateRequest;
import com.carterprojects.movienightmanager.model.user.UserCredentials;
import com.carterprojects.movienightmanager.repository.AppUserRepository;
import com.carterprojects.movienightmanager.repository.models.user.AppUser;
import com.carterprojects.movienightmanager.repository.models.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    AppUserRepository appUserRepository;
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserRepository.findAppUserDetailsByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username: " + username + " not found"));
    }

    public List<AppUser> getAllUsers() {
        return appUserRepository.findAll();
    }

    public AppUser createUserFromRequest(UserCreateRequest createRequest) throws MnmAppException {
        var existingUser = appUserRepository.findAppUserByEmailOrUsername(createRequest.getEmail(), createRequest.getUsername());
        if (existingUser.isPresent()) {
            if (existingUser.get().getEmail().equalsIgnoreCase(createRequest.getEmail())) {
                throw new MnmAppException("A user with this email already exists");
            } else {
                throw new MnmAppException("A user with this username already exists");
            }
        }

        var newUser =
                AppUser.builder()
                        .userRole(UserRole.USER)
                        .email(createRequest.getEmail())
                        .firstName(createRequest.getFirstName())
                        .lastName(createRequest.getLastName())
                        .username(createRequest.getUsername())
                        .password(passwordEncoder.encode(createRequest.getPassword()))
                        .build();

        return appUserRepository.save(newUser);
    }

    @Override
    public Optional<AppUser> getUserByCredentials(UserCredentials creds) {
        return appUserRepository.findAppUserByUsername(creds.getUsername());
    }

    @Override
    public Optional<AppUser> getUserById(Integer userId) {
        return appUserRepository.findById(userId);
    }

    public void deleteUserAccount(Integer userId) throws MnmAppException {

        var user = appUserRepository.findById(userId)
                .orElseThrow(
                        () -> {
                            var errorStr = String.format(
                                    "No user found with user id: %d ",
                                    userId
                            );
                            log.error(errorStr);
                            return new MnmAppException(errorStr);
                        }
                );

        try {
            appUserRepository.delete(user);
        } catch (Exception ex) {
            log.error("Could not delete exception", ex);
            throw new MnmAppException("Error while deleting user account, please try again later");
        }
    }

}
