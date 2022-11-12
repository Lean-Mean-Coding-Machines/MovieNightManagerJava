package com.carterprojects.movienightmanager.repository.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "nomination")
public class Nomination {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "nomination_id")
    Integer id;

    String movieTitle;

    Boolean chosen;

    LocalDateTime username;

    WatchType watchType;

    LocalDateTime segmentEndDate;

    @ManyToOne
    @JoinColumn(name = "movie_night_segment_id", nullable = false)
    MovieNightSegment movieNightSegment;

    @OneToMany(mappedBy = "nomination")
    List<NominationLike> nominationLikes;

    @ManyToOne
    @JoinColumn(name = "app_user_id", nullable = false)
    AppUser user;
}
