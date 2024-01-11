package com.carterprojects.movienightmanager.service;

import com.carterprojects.movienightmanager.model.tmdb.TmdbMovieDetails;
import com.carterprojects.movienightmanager.model.tmdb.TmdbResult;
import com.carterprojects.movienightmanager.model.tmdb.TmdbSearchResult;

import java.util.List;

public interface TmdbService {
    TmdbResult<List<TmdbSearchResult>> searchMovies(String title);

    TmdbMovieDetails getMovieDetails(Integer id);
}