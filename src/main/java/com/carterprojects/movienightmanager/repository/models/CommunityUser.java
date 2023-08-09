package com.carterprojects.movienightmanager.repository.models;

import com.carterprojects.movienightmanager.repository.models.user.AppUser;
import com.carterprojects.movienightmanager.repository.models.user.CommunityRole;
import com.carterprojects.movienightmanager.repository.models.user.UserRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommunityUser {
    @EmbeddedId
    CommunityUserKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    AppUser user;

    @ManyToOne
    @MapsId("communityId")
    @JoinColumn(name = "community_id")
    Community community;

    @Column(name = "community_role")
    @Enumerated(EnumType.STRING)
    CommunityRole communityRole;
}
