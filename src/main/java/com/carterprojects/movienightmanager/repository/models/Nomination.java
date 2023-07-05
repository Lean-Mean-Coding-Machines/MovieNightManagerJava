package com.carterprojects.movienightmanager.repository.models;

import com.carterprojects.movienightmanager.repository.models.user.AppUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
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

    String movieTitle;

    Boolean chosen;

    @ManyToOne(optional = false)
    @JoinColumn(name = "movie_night_segment_id", nullable = false)
    MovieNightSegment movieNightSegment;

    @OneToMany(mappedBy = "nomination")
    List<NominationLike> nominationLikes;

    @ManyToOne(optional = false)
    @JoinColumn(name = "app_user_id",  referencedColumnName = "user_id", nullable = false)
    AppUser user;
}
