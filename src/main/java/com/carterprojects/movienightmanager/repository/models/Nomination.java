package com.carterprojects.movienightmanager.repository.models;

import com.carterprojects.movienightmanager.repository.models.user.AppUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "nomination")
public class Nomination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nomination_id")
    Integer id;

    Integer movieId;

    String movieTitle;

    Boolean chosen;

    String posterPath;

    String movieOverview;

    String releaseDate;

    Integer runtime;

    String genres;

    @ManyToOne(optional = false)
    @JoinColumn(name = "movie_night_segment_id", nullable = false)
    MovieNightSegment movieNightSegment;

    @OneToMany(mappedBy = "nomination", cascade = CascadeType.REMOVE, orphanRemoval = true)
    List<NominationLike> nominationLikes;

    @ManyToOne(optional = false)
    @JoinColumn(name = "app_user_id", referencedColumnName = "user_id", nullable = false)
    AppUser user;
}
