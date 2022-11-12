package com.carterprojects.movienightmanager.repository.models;

import com.carterprojects.movienightmanager.repository.UserRole;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "movie_night_segment")
public class MovieNightSegment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "movie_night_segment_id")
    Integer id;

    LocalDateTime nominationStartDate;

    LocalDateTime nominationLockDate;

    LocalDateTime username;

    WatchType watchType;

    LocalDateTime segmentEndDate;

    @OneToMany(mappedBy = "movie_night_segment")
    List<Nomination> nominations;
}
