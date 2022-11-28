package com.carterprojects.movienightmanager.mapper;

import com.carterprojects.movienightmanager.model.NominationDto;
import com.carterprojects.movienightmanager.model.NominationLikeDto;
import com.carterprojects.movienightmanager.repository.models.Nomination;
import com.carterprojects.movienightmanager.repository.models.NominationLike;

import java.util.stream.Collectors;

public class NominationsMapper {

    public static NominationDto nominationToNominationDto(Nomination nomination) {
        return NominationDto.builder()
                .id(nomination.getId())
                .movieTitle(nomination.getMovieTitle())
                .chosen(nomination.getChosen())
                .submittedBy(nomination.getUser().getUsername())
                .build();
    }

    public static NominationDto.NominationWithLikesDto nominationToNominationWithLikesDto(Nomination nomination) {
        var nomWithLikes = new NominationDto.NominationWithLikesDto();
        nomWithLikes.setId(nomination.getId());
        nomWithLikes.setChosen(nomination.getChosen());
        nomWithLikes.setMovieTitle(nomination.getMovieTitle());
        nomWithLikes.setSubmittedBy(nomination.getUser().getUsername());
        nomWithLikes.setNominationLikes(
                nomination.getNominationLikes()
                        .stream()
                        .map(NominationsMapper::nominationLikeToNominationLikeDto)
                        .collect(Collectors.toList())
        );

        return nomWithLikes;
    }

    public static NominationLikeDto nominationLikeToNominationLikeDto(NominationLike nominationLike) {
        return NominationLikeDto.builder()
                .id(nominationLike.getId())
                .enabled(nominationLike.getEnabled())
                .preferredWatchDate(nominationLike.getPreferredWatchDate().toString())
                .preferredWatchType(nominationLike.getPreferredWatchType().name())
                .build();
    }

}
