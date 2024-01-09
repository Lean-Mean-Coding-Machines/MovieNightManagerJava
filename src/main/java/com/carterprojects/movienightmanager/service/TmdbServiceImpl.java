package com.carterprojects.movienightmanager.service;
import com.carterprojects.movienightmanager.model.tmdb.TmdbMovieDetails;
import com.carterprojects.movienightmanager.model.tmdb.TmdbResult;
import com.carterprojects.movienightmanager.model.tmdb.TmdbSearchResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class TmdbServiceImpl implements TmdbService {

    @Autowired
    WebClient tmdbClient;

    @Value("${tmdb.api.movie-search-url}")
    String searchUrl;

    @Value("${tmdb.api.movie-details-url}")
    String detailsUrl;


    public TmdbResult<List<TmdbSearchResult>> searchMovies(String title) {
        try {
            return tmdbClient.get()
                    .uri(uriBuilder ->
                            uriBuilder
                                    .path(searchUrl)
                                    .queryParam("query", title)
                                    .queryParam("page", 1)
                                    .build()
                    )
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<TmdbResult<List<TmdbSearchResult>>>() {
                    })
                    .block(); // This can be unblocked for a more asynchronous approach in the future
        } catch (WebClientException ex) {
            log.error("Could not complete tmdb api request", ex);
            return new TmdbResult<>(0, 0, 0, Collections.emptyList());
        }
    }

    public TmdbMovieDetails  getMovieDetails(Integer id) {
        try {
            return tmdbClient.get()
                    .uri(uriBuilder ->
                            uriBuilder
                                    .path(detailsUrl + "/" + id)
                                    .build()
                    )
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<TmdbMovieDetails>() {
                    })
                    .block(); // This can be unblocked for a more asynchronous approach in the future
        } catch (Exception ex) {
            log.error("Could not complete tmdb api request", ex);
            return new TmdbMovieDetails();
        }
    }


}