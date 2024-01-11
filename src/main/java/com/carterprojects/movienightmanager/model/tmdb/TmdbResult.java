package com.carterprojects.movienightmanager.model.tmdb;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TmdbResult<T> {
    Integer page;
    @JsonProperty(value = "total_pages")
    Integer totalPages;
    @JsonProperty(value = "total_results")
    Integer totalResults;
    T results;
}
