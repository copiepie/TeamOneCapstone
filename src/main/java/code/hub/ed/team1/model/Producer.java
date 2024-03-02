package code.hub.ed.team1.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@SuperBuilder
@DiscriminatorValue(value = Profession.Val._PRODUCER)
public class Producer extends People {
    @ManyToMany(mappedBy = "producers")
    protected Set<Movie> movies;

    @ManyToMany(mappedBy = "producers")
    protected Set<TvShow> tvShows;

    public void addMovies(Movie... moviesToAdd) {
        if (movies == null) {
            movies = new HashSet<>();
        }
        movies.addAll(Set.of(moviesToAdd));
    }

    public void addTvShows(TvShow... tvShowsToAdd) {
        if (tvShows == null) {
            tvShows = new HashSet<>();
        }
        tvShows.addAll(Set.of(tvShowsToAdd));
    }
}
