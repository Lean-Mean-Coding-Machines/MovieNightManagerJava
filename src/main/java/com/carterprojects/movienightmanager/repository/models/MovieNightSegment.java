package com.carterprojects.movienightmanager.repository.models;

import jakarta.persistence.*;
import com.carterprojects.movienightmanager.repository.models.user.AppUser;
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
@Table(name = "movie_night_segment")
public class MovieNightSegment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_night_segment_id")
    Integer id;

    LocalDateTime nominationStartDate;

    LocalDateTime nominationLockDate;

    LocalDateTime chosenWatchDate;

    LocalDateTime segmentEndDate;

    @OneToMany(mappedBy = "movieNightSegment")
    List<Nomination> nominations;

    @ManyToOne
    @MapsId("communityId")
    @JoinColumn(name = "community_id")
    Community community;

    @ManyToOne(optional = false)
    @JoinColumn(name = "app_user_id", referencedColumnName = "user_id", nullable = false)
    AppUser user;
}
