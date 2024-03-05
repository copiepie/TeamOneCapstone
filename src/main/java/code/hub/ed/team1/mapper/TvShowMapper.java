package code.hub.ed.team1.mapper;

import code.hub.ed.team1.dto.TvShowDto;
import code.hub.ed.team1.model.TvShow;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TvShowMapper {

	TvShowMapper INSTANCE = Mappers.getMapper(TvShowMapper.class);
	
	TvShowDto tvShowToTvShowDto(TvShow tvShow);
	
	TvShow tvShowDtoToTvShow(TvShowDto tvShowDto);
	
	List<TvShowDto> tvShowToTvShowDtoList(List<TvShow> tvShows);
	
}
