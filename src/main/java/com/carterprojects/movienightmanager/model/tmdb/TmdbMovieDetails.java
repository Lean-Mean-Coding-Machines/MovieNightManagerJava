package com.carterprojects.movienightmanager.model.tmdb;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TmdbMovieDetails {
    Integer runtime;
    List<TmdbMovieGenre> genres;
    String overview;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TmdbMovieGenre {
        Integer id;
        String name;
    }
}
