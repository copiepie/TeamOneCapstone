package code.hub.ed.team1.service.api;

import code.hub.ed.team1.dto.MovieDto;

public interface MovieService extends GeneralService<MovieDto, Long> {
    boolean hasMovies();
}
