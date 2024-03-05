package code.hub.ed.team1.repository;

import code.hub.ed.team1.model.Genre;
import code.hub.ed.team1.model.TvShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TvShowRepository extends JpaRepository<TvShow, Long> {
	
	List<TvShow> findByGenre(Genre genre);
	
	List<TvShow> findByStartingYear(Integer year);
	
	List<TvShow> findByTitleContaining(String title);
	
	
}
