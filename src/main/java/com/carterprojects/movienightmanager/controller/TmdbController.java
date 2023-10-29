package com.carterprojects.movienightmanager.controller;

import com.carterprojects.movienightmanager.controller.security.Authorize;

import com.carterprojects.movienightmanager.model.MnmApiResponse;
import com.carterprojects.movienightmanager.service.TmdbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;

@RestController
@RequestMapping("/v1/tmdb")
public class TmdbController {
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
}