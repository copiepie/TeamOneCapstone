package code.hub.ed.team1.service.impl;

import code.hub.ed.team1.dto.MovieDto;
import code.hub.ed.team1.exception.MovieNotFoundException;
import code.hub.ed.team1.mapper.MovieMapper;
import code.hub.ed.team1.model.*;
import code.hub.ed.team1.repository.MovieRepository;
import code.hub.ed.team1.repository.PeopleRepository;
import code.hub.ed.team1.service.api.Calculatable;
import code.hub.ed.team1.service.api.MovieService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService, Calculatable {

  private static final Logger LOGGER = LoggerFactory.getLogger(MovieServiceImpl.class.getName());

  private static final String NO_MOVIE_FOUND_MSG = "No Movie with id '%d' could be found";

  private final MovieRepository movieRepository;

  private final PeopleRepository peopleRepository;

  private final MovieMapper movieMapper;

  @Override
  public MovieDto create(MovieDto movieDto) {
    Movie movie = movieMapper.movieDtoToMovie(movieDto);
    Movie persistedMovie = movieRepository.save(movie);
    savePeopleRelationship(persistedMovie);
    return movieMapper.movieToMovieDto(movie);
  }

  public void savePeopleRelationship(final Movie movie) {
    Director director = movie.getDirector();
    director.addMovies(movie);

    Set<Actor> actors = movie.getActors();
    actors.stream().forEach(actor -> actor.addMovies(movie));

    Set<CrewMember> crewMembers = movie.getCrewMembers();
    crewMembers.stream().forEach(crewMember -> crewMember.addMovies(movie));

    Set<Producer> producers = movie.getProducers();
    producers.stream().forEach(producer -> producer.addMovies(movie));

    Set<People> allAfiliatedPeople = new HashSet<>();
    allAfiliatedPeople.add(director);
    allAfiliatedPeople.addAll(actors);
    allAfiliatedPeople.addAll(crewMembers);
    allAfiliatedPeople.addAll(producers);

    peopleRepository.saveAll(allAfiliatedPeople);
  }

  @Override
  public MovieDto read(Long id) {
    Optional<Movie> optionalMovie = movieRepository.findById(id);
    Movie movie =
        optionalMovie.orElseThrow(
            () -> new MovieNotFoundException(String.format(NO_MOVIE_FOUND_MSG, id)));
    return movieMapper.movieToMovieDto(movie);
  }

  @Override
  public MovieDto update(MovieDto movieDto) {
    Optional<Movie> optionalMovie = movieRepository.findById(movieDto.getId());
    if (optionalMovie.isPresent()) {
      Movie movie = movieMapper.movieDtoToMovie(movieDto);
      movie = movieRepository.save(movie);
      savePeopleRelationship(movie);
      return movieMapper.movieToMovieDto(movie);
    } else {
      String msg = String.format(NO_MOVIE_FOUND_MSG, movieDto.getId());
      LOGGER.error(msg);
      throw new MovieNotFoundException(msg);
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
            () -> new MovieNotFoundException(String.format(NO_MOVIE_FOUND_MSG, id)));
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

  @Override
  public boolean hasMovies() {
    return movieRepository.hasMovies();
  }
}
