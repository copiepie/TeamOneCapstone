package code.hub.ed.team1.mapper;

import code.hub.ed.team1.dto.ActorDto;
import code.hub.ed.team1.model.Actor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ActorMapper {


  Actor actorDtoToActor(ActorDto actorDto);

  ActorDto actorToActorDto(Actor actor);
}
