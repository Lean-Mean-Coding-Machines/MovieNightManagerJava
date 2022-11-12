package com.carterprojects.movienightmanager.repository.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "nomination_like")
public class NominationLike {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "nomination_like_id")
    Integer id;

    Boolean enabled;

    @Column(name = "prefer_watch_type")
    WatchType preferredWatchType;

    @Column(name = "prefer_watch_date")
    LocalDateTime preferredWatchDate;

    LocalDateTime segmentEndDate;

    @ManyToOne
    @JoinColumn(name = "nomination_id", nullable = false)
    Nomination nomination;

    @ManyToOne
    @JoinColumn(name = "app_user_id", nullable = false)
    AppUser user;
}
