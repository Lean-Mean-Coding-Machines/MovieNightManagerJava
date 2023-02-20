package com.carterprojects.movienightmanager.repository.models;

import com.carterprojects.movienightmanager.repository.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "app_user")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    Integer id;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    UserRole userRole;

    String firstName;

    String lastName;

    String username;

    String password;

    String email;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    List<Nomination> nominations;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    List<NominationLike> nominationLikes;
}
