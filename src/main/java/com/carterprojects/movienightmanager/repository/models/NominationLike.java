package com.carterprojects.movienightmanager.repository.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "nomination_like")
public class NominationLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nomination_like_id")
    Integer id;

    Boolean enabled;

    @Column(name = "prefer_watch_type")
    WatchType preferredWatchType;

    @Column(name = "prefer_watch_date_time")
    LocalDateTime preferredWatchDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nomination_id", nullable = false)
    Nomination nomination;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_user_id", nullable = false)
    AppUser user;
}