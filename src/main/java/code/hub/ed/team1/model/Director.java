package code.hub.ed.team1.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@SuperBuilder
@Getter
@Setter
@DiscriminatorValue(value = Profession.Val._DIRECTOR)
public class Director extends People {

    @OneToMany(mappedBy = "director")
    private Set<Movie> movies;

    @OneToMany(mappedBy = "director")
    private Set<TvShow> tvShows;

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
