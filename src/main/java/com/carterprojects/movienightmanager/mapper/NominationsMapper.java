package com.carterprojects.movienightmanager.mapper;

import com.carterprojects.movienightmanager.model.nomination.NominationDto;
import com.carterprojects.movienightmanager.model.nomination.NominationLikeDto;
import com.carterprojects.movienightmanager.repository.models.Nomination;
import com.carterprojects.movienightmanager.repository.models.NominationLike;

import java.util.List;
import java.util.stream.Collectors;

public class NominationsMapper {

    public static NominationDto nominationToNominationDto(Nomination nomination) {
        return NominationDto.builder()
                .id(nomination.getId())
                .movieId(nomination.getMovieId())
                .movieTitle(nomination.getMovieTitle())
                .chosen(nomination.getChosen())
                .submittedBy(nomination.getUser().getUsername())
                .posterPath(nomination.getPosterPath())
                .movieOverview(nomination.getMovieOverview())
                .releaseDate(nomination.getReleaseDate())
                .genres(List.of(nomination.getGenres().split(",")))
                .runtime(nomination.getRuntime())
                // .userLikes(nomination.getNominationLikes().stream().map(nominationLike -> nominationLike.getUser().getUsername()).collect(Collectors.toList()))
                .build();
    }

    public static NominationDto.NominationWithLikesDto nominationToNominationWithLikesDto(Nomination nomination) {
        var nomWithLikes = new NominationDto.NominationWithLikesDto();
        nomWithLikes.setId(nomination.getId());
        nomWithLikes.setMovieId(nomination.getMovieId());
        nomWithLikes.setChosen(nomination.getChosen());
        nomWithLikes.setMovieTitle(nomination.getMovieTitle());
        nomWithLikes.setPosterPath(nomination.getPosterPath());
        nomWithLikes.setMovieOverview(nomination.getMovieOverview());
        nomWithLikes.setReleaseDate(nomination.getReleaseDate());
        nomWithLikes.setGenres(List.of(nomination.getGenres().split(",")));
        nomWithLikes.setRuntime(nomination.getRuntime());
        nomWithLikes.setSubmittedBy(nomination.getUser().getUsername());
        nomWithLikes.setNominationLikes(
                nomination.getNominationLikes()
                        .stream()
                        .filter(NominationLike::getEnabled)
                        .map(NominationsMapper::nominationLikeToNominationLikeDto)
                        .collect(Collectors.toList())
        );

        return nomWithLikes;
    }

    public static NominationLikeDto nominationLikeToNominationLikeDto(NominationLike nominationLike) {
        return NominationLikeDto.builder()
                .id(nominationLike.getId())
                .enabled(nominationLike.getEnabled())
                .userId(nominationLike.getUser().getId())
                .username(nominationLike.getUser().getUsername())
                .preferredWatchDate(nominationLike.getPreferredWatchDate().toString())
                .preferredWatchType(nominationLike.getPreferredWatchType().name())
                .build();
    }

}
