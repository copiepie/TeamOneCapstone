package code.hub.ed.team1.service.impl;

import code.hub.ed.team1.dto.ActorDto;
import code.hub.ed.team1.mapper.ActorMapper;
import code.hub.ed.team1.model.Actor;
import code.hub.ed.team1.repository.ActorRepository;
import code.hub.ed.team1.service.api.ActorService;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ActorServiceImpl implements ActorService {

  private ActorRepository actorRepository;

  private ActorMapper actorMapper;


  public ActorDto create(ActorDto actorDto) {
    Actor actor = actorMapper.actorDtoToActor(actorDto);
    actorRepository.save(actor);
    return actorMapper.actorToActorDto(actor);
  }


  public ActorDto read(Long id) {
    Optional<Actor> optionalActor = actorRepository.findById(id);
    Actor actor = optionalActor.orElseThrow(IllegalArgumentException::new);
    return actorMapper.actorToActorDto(actor);
  }


  public ActorDto update(ActorDto actorDto) {
    Optional<Actor> optionalActor = actorRepository.findById(actorDto.getId());
    if (optionalActor.isPresent()) {
      Actor actor = actorMapper.actorDtoToActor(actorDto);
      actorRepository.save(actor);
      return actorMapper.actorToActorDto(actor);
    } else {
      // TODO Change to somehting like MovieNotFoundException
      throw new IllegalArgumentException("Actor not found");
    }
  }


  public void delete(Long id) {
    actorRepository.deleteById(id);
  }
}
