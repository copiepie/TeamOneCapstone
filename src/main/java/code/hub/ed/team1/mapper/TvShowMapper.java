package code.hub.ed.team1.mapper;

import code.hub.ed.team1.dto.MovieDto;
import code.hub.ed.team1.dto.TvShowDto;
import code.hub.ed.team1.model.Movie;
import code.hub.ed.team1.model.TvShow;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TvShowMapper {

    TvShowDto tvShowToTvShowDto(TvShow tvShow);
}
