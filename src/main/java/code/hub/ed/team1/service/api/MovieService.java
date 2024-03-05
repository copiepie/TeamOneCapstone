package code.hub.ed.team1.service.api;

import code.hub.ed.team1.dto.MovieDto;

public interface MovieService extends Calculatable, GeneralService<MovieDto, Long> {
  boolean hasMovies();
}
