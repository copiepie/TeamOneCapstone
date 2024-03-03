package code.hub.ed.team1.mapper;

import code.hub.ed.team1.dto.MovieDto;
import code.hub.ed.team1.model.Movie;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieMapper {

    MovieDto movieToMovieDto(Movie movie);

    Movie movieDtoToMovie(MovieDto movieDto);
}
