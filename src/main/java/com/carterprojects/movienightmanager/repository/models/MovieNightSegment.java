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

    @OneToMany(mappedBy = "movieNightSegment", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Nomination> nominations;

    @ManyToOne(optional = false)
    @JoinColumn(name = "community_id", referencedColumnName = "community_id", nullable = false)
    Community community;

    @ManyToOne(optional = false)
    @JoinColumn(name = "app_user_id", referencedColumnName = "user_id", nullable = false)
    AppUser user;
}
