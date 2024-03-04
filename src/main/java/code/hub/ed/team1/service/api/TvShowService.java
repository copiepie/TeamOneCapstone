package code.hub.ed.team1.service.api;

import code.hub.ed.team1.dto.TvShowDto;
import code.hub.ed.team1.model.Genre;
import code.hub.ed.team1.model.TvShow;

import java.util.List;

public interface TvShowService extends GeneralService<TvShowDto, Long> {
	
	List<TvShow> findTvShowsByGenre(Genre genre);
	
	List<TvShowDto> findTvShowsByYear(Integer year);
	
	List<TvShow> findTvShowsByTitle(String title);
	
	
	
	
}
