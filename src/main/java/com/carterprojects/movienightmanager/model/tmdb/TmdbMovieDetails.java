package com.carterprojects.movienightmanager.model.tmdb;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TmdbMovieDetails {
    Integer runtime;
    List<TmdbMovieGenre> genres;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TmdbMovieGenre {
        Integer id;
        String name;
    }
}
