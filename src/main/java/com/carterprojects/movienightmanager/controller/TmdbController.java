package com.carterprojects.movienightmanager.controller;

import com.carterprojects.movienightmanager.controller.security.Authorize;
import com.carterprojects.movienightmanager.model.MnmApiResponse;
import com.carterprojects.movienightmanager.service.TmdbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;

@RestController
@RequestMapping("/v1/tmdb")
public class
TmdbController {
    @Autowired
    TmdbService tmdbServiceImpl;

    @Authorize
    @GetMapping("/movie/search")
    public ResponseEntity<MnmApiResponse> searchForMovieByTitle(@QueryParam("title") String title) {
        if (title == null || title.isEmpty()) {
            return MnmApiResponse.failed("Title cannot be empty", HttpStatus.BAD_REQUEST);
        }
        return MnmApiResponse.success(tmdbServiceImpl.searchMovies(title));
    }

    @Authorize
    @GetMapping("/movie/{movieId}")
    public ResponseEntity<MnmApiResponse> searchForMovieDetailsById(@PathVariable Integer movieId) {
        if (movieId == null) {
            return MnmApiResponse.failed("Id cannot be empty", HttpStatus.BAD_REQUEST);
        }
        return MnmApiResponse.success(tmdbServiceImpl.getMovieDetails(movieId));
    }
}