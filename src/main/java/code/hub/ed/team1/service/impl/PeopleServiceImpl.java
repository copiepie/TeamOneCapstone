package code.hub.ed.team1.service.impl;

import code.hub.ed.team1.dto.MovieDto;
import code.hub.ed.team1.dto.PeopleDto;
import code.hub.ed.team1.dto.TvShowDto;
import code.hub.ed.team1.mapper.MovieMapper;
import code.hub.ed.team1.exception.PersonNotFoundException;
import code.hub.ed.team1.exception.ProfessionDoesNotExistException;
import code.hub.ed.team1.mapper.PeopleMapper;
import code.hub.ed.team1.mapper.TvShowMapper;
import code.hub.ed.team1.model.*;
import code.hub.ed.team1.repository.*;
import code.hub.ed.team1.service.api.PeopleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PeopleServiceImpl implements PeopleService {
  private static final String PERSON_NOT_FOUND_MSG = "The Person with the ID %s doesn't exist";
  private static final String PROFESSION_DOES_NOT_EXIST_MSG = "The profession %s doesn't exist.";

  private final ActorRepository actorRepository;

  private final ProducerRepository producerRepository;

  private final CrewMemberRepository crewMemberRepository;

  private final DirectoryRepository directoryRepository;

  private final PeopleRepository peopleRepository;

  private final PeopleMapper peopleMapper;

  private final MovieMapper movieMapper;

  private final TvShowMapper tvShowMapper;

  private People save(PeopleDto peopleDto) {
    switch (peopleDto.getProfession()) {
      case ACTOR -> {
        Actor actor = peopleMapper.peopleDtoToActor(peopleDto);
        return actorRepository.save(actor);
      }
      case DIRECTOR -> {
        Director director = peopleMapper.peopleDtoToDirector(peopleDto);
        return directoryRepository.save(director);
      }
      case CREW_MEMBER -> {
        CrewMember crewMember = peopleMapper.peopleDtoToCrewMember(peopleDto);
        return crewMemberRepository.save(crewMember);
      }
      case PRODUCER -> {
        Producer producer = peopleMapper.peopleDtoToProducer(peopleDto);
        return producerRepository.save(producer);
      }
      default -> throw new ProfessionDoesNotExistException(
          String.format(PROFESSION_DOES_NOT_EXIST_MSG, peopleDto.getProfession()));
    }
  }

  @Override
  public PeopleDto create(PeopleDto peopleDto) {
    return peopleMapper.peopleToPeopleDto(save(peopleDto));
  }

  @Override
  public PeopleDto read(Long id) {
    Optional<People> optionalPeople = peopleRepository.findById(id);
    People people = optionalPeople.orElseThrow(IllegalArgumentException::new);
    return peopleMapper.peopleToPeopleDto(people);
  }

  @Override
  public PeopleDto update(PeopleDto peopleDto) {
    Optional<People> optionalPeople = peopleRepository.findById(peopleDto.getId());
    optionalPeople.orElseThrow(
        () -> new PersonNotFoundException(String.format(PERSON_NOT_FOUND_MSG, peopleDto.getId())));
    return peopleMapper.peopleToPeopleDto(save(peopleDto));
  }

  @Override
  public void delete(Long id) {
    peopleRepository.deleteById(id);
  }


  @Override
  public PeopleDto findByIdWithMoviesAndTvShows(Long id) {
    Optional<People> optionalPeople = peopleRepository.findById(id);
    People people = optionalPeople.orElseThrow(IllegalArgumentException::new);

    Set<MovieDto> movieDtos = people.getMovies().stream()
            .map(movieMapper::movieToMovieDto)
            .collect(Collectors.toSet());

    Set<TvShowDto> tvShowDtos = people.getTvShows().stream()
            .map(tvShowMapper::tvShowToTvShowDto)
            .collect(Collectors.toSet());

    PeopleDto peopleDto = peopleMapper.peopleToPeopleDto(people);

    peopleDto.setMovies(movieDtos);
    peopleDto.setTvShows(tvShowDtos);

    return peopleDto;
  }
}
