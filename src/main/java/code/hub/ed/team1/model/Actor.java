package code.hub.ed.team1.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue(value = Profession.Val._ACTOR)
public class Actor extends People {

    @ManyToMany(mappedBy = "actors")
    private Set<Movie> movies;

    @ManyToMany(mappedBy = "actors")
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
