package com.carterprojects.movienightmanager.model.tmdb;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TmdbSearchResult {
    Integer id;
    String title;
    @JsonAlias({"poster_path"})
    String posterPath;
    @JsonAlias({"release_date"})
    String releaseDate;
    String overview;
}
