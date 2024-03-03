package code.hub.ed.team1.repository;

import code.hub.ed.team1.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("select count(m) > 0 from Movie m")
    boolean hasMovies();

    void deleteById(Long id);
}
