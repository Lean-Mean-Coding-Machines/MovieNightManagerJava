package com.carterprojects.movienightmanager.repository.models;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
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

    WatchType watchType;

    LocalDateTime segmentEndDate;

    @OneToMany(mappedBy = "movieNightSegment")
    List<Nomination> nominations;
}
