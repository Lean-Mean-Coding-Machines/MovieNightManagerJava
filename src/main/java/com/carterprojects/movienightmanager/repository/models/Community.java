package com.carterprojects.movienightmanager.repository.models;

import com.carterprojects.movienightmanager.repository.models.user.AppUser;
import com.carterprojects.movienightmanager.repository.models.CommunityUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "community")
public class Community {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "community_id")
    Integer id;

    @Column(name = "name")
    String communityName;

    String timezone;

    LocalDateTime createdOn;

    @Column(name = "created_by")
    String createdByUsername;

    @OneToMany(mappedBy = "community")
    List<CommunityUser> communityUsers;

    @OneToMany(mappedBy = "community")
    List<MovieNightSegment> movieNightSegments;
}