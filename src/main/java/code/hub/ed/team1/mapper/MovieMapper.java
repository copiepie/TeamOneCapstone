package code.hub.ed.team1.mapper;

import code.hub.ed.team1.dto.MovieDto;
import code.hub.ed.team1.model.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

    MovieDto movieToMovieDto(Movie movie);

    Movie movieDtoToMovie(MovieDto movieDto);
}
