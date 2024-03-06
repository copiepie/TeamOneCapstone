package code.hub.ed.team1.service.impl;

import code.hub.ed.team1.dto.MovieDto;
import code.hub.ed.team1.exception.MovieNotFoundException;
import code.hub.ed.team1.mapper.MovieMapper;
import code.hub.ed.team1.model.Movie;
import code.hub.ed.team1.model.People;
import code.hub.ed.team1.model.SalaryType;
import code.hub.ed.team1.repository.MovieRepository;
import code.hub.ed.team1.service.api.Calculatable;
import code.hub.ed.team1.service.api.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService, Calculatable {

  private final MovieRepository movieRepository;

  private final MovieMapper movieMapper;

  @Override
  public MovieDto create(MovieDto movieDto) {
    // TODO inverse side of relationship must be updated, Actor, CrewMember, Director, Producers
    Movie movie = movieMapper.movieDtoToMovie(movieDto);
    movieRepository.save(movie);
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
      movieRepository.save(movie);
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

  @Override
  public BigDecimal calculateCost(long id) {
    Optional<Movie> optionalMovie = movieRepository.findById(id);
    Movie movie =
        optionalMovie.orElseThrow(
            () ->
                new MovieNotFoundException(
                    String.format("No Movie with id '%d' could be found", id)));
    BigDecimal directorSalary = calculateSalaryBasedOnType(movie.getDirector());
    BinaryOperator<BigDecimal> accumulator = (sub, sal) -> sub.add(sal);
    BigDecimal actorsTotalSalary =
        movie.getActors().stream()
            .map(this::calculateSalaryBasedOnType)
            .reduce(BigDecimal.ZERO, accumulator);
    BigDecimal producersTotalSalary =
        movie.getProducers().stream()
            .map(this::calculateSalaryBasedOnType)
            .reduce(BigDecimal.ZERO, accumulator);
    BigDecimal crewMembersTotalSalary =
        movie.getCrewMembers().stream()
            .map(this::calculateSalaryBasedOnType)
            .reduce(BigDecimal.ZERO, accumulator);

    return Stream.of(
            directorSalary, actorsTotalSalary, producersTotalSalary, crewMembersTotalSalary)
        .reduce(BigDecimal.ZERO, accumulator);
  }

  private BigDecimal calculateSalaryBasedOnType(People person) {
    if (person.getSalaryType() == SalaryType.SINGLE_EPISODE) {
      return person.getSalary().multiply(BigDecimal.valueOf(20));
    }
    return person.getSalary();
  }
}
