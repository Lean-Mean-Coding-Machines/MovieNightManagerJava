package com.carterprojects.movienightmanager.repository.models;

import com.carterprojects.movienightmanager.repository.UserRole;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "app_user")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    Integer id;

    @Enumerated(EnumType.STRING)
    UserRole userRole;

    String firstName;

    String lastName;

    String username;

    String password;

    String email;

    @OneToMany(mappedBy = "app_user")
    List<Nomination> nominations;

    @OneToMany(mappedBy = "app_user")
    List<NominationLike> nominationLikes;
}
