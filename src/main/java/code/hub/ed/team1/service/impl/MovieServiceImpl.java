package code.hub.ed.team1.service.impl;

import code.hub.ed.team1.dto.MovieDto;
import code.hub.ed.team1.mapper.MovieMapper;
import code.hub.ed.team1.model.Movie;
import code.hub.ed.team1.repository.MovieRepository;
import code.hub.ed.team1.service.api.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {

  private final MovieRepository movieRepository;

  private final MovieMapper movieMapper;

  @Override
  public MovieDto create(MovieDto movieDto) {
    // TODO inverse side of relationship must be updated, Actor, CrewMember, Director, Producers
    Movie movie = movieMapper.movieDtoToMovie(movieDto);
    movie = movieRepository.save(movie);
    return movieMapper.movieToMovieDto(movie);
  }

  @Override
  public MovieDto read(Long id) {
    Optional<Movie> optionalMovie = movieRepository.findById(id);
    Movie movie = optionalMovie.orElseThrow(IllegalArgumentException::new);
    return movieMapper.movieToMovieDto(movie);
  }

  @Override
  public MovieDto update(MovieDto movieDto) {
    Optional<Movie> optionalMovie = movieRepository.findById(movieDto.getId());
    if (optionalMovie.isPresent()) {
      Movie movie = movieMapper.movieDtoToMovie(movieDto);
      movie = movieRepository.save(movie);
      return movieMapper.movieToMovieDto(movie);
    } else {
      // TODO Change to somehting like MovieNotFoundException
      throw new IllegalArgumentException("Movie not found");
    }
  }

  @Override
  public void delete(Long id) {
    movieRepository.deleteById(id);
  }
}
