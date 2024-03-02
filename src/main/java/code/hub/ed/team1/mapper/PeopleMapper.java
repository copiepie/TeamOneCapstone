package code.hub.ed.team1.mapper;

import code.hub.ed.team1.dto.PeopleDto;
import code.hub.ed.team1.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PeopleMapper {

  PeopleMapper INSTANCE = Mappers.getMapper(PeopleMapper.class);

  PeopleDto peopleToPeopleDto(People people);

  Actor peopleDtoToActor(PeopleDto peopleDto);

  Director peopleDtoToDirector(PeopleDto peopleDto);

  CrewMember peopleDtoToCrewMember(PeopleDto peopleDto);

  Producer peopleDtoToProducer(PeopleDto peopleDto);

  PeopleDto actorToPeopleDto(Actor actor);

  PeopleDto directorToPeopleDto(Director director);

  PeopleDto producerToPeopleDto(Producer producer);

  PeopleDto crewMemberToPeopleDto(CrewMember crewMember);
}
