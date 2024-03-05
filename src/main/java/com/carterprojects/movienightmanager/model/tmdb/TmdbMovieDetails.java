package com.carterprojects.movienightmanager.model.tmdb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TmdbMovieDetails {
    Integer runtime;
    List<TmdbMovieGenre> genres;
    String overview;
    TmdbMovieCredits credits;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TmdbMovieGenre {
        Integer id;
        String name;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TmdbMovieCredits {
        List<TmdbMovieCast> cast;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TmdbMovieCast {
        String known_for_department;
        String name;
    }
}
