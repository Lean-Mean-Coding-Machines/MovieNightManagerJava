package com.carterprojects.movienightmanager.repository.models;

import com.carterprojects.movienightmanager.repository.models.user.AppUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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

    LocalDateTime modifiedOn;

    Boolean active;

    @ManyToOne(optional = false)
    @JoinColumn(name = "app_user_id", referencedColumnName = "user_id", nullable = false)
    AppUser createdBy;

    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL, orphanRemoval = true)
    List<CommunityUser> communityUsers;

    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL, orphanRemoval = true)
    List<MovieNightSegment> movieNightSegments;
}