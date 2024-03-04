package code.hub.ed.team1.mapper;

import code.hub.ed.team1.dto.TvShowDto;
import code.hub.ed.team1.model.TvShow;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TvShowMapper {
	
	TvShowDto tvShowToTvShowDto(TvShow tvShow);
	
	TvShow tvShowDtoToTvShow(TvShowDto tvShowDto);
	
	List<TvShowDto> tvShowToTvShowDtoList(List<TvShow> tvShows);
	
}
