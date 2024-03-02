package code.hub.ed.team1.service.impl;

import code.hub.ed.team1.dto.PeopleDto;
import code.hub.ed.team1.mapper.PeopleMapper;
import code.hub.ed.team1.model.*;
import code.hub.ed.team1.repository.*;
import code.hub.ed.team1.service.api.PeopleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class PeopleServiceImpl implements PeopleService {

  private final ActorRepository actorRepository;

  private final ProducerRepository producerRepository;

  private final CrewMemberRepository crewMemberRepository;

  private final DirectoryRepository directoryRepository;

  private final PeopleRepository peopleRepository;

  private final PeopleMapper peopleMapper;

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
      default -> throw new IllegalArgumentException(
          String.format("The profession %s doesn't exist.", peopleDto.getProfession()));
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
    return peopleMapper.peopleToPeopleDto(save(peopleDto));
  }

  @Override
  public void delete(Long id) {
    peopleRepository.deleteById(id);
  }
}
