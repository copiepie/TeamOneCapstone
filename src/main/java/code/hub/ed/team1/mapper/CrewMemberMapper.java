package code.hub.ed.team1.mapper;

import code.hub.ed.team1.dto.CrewMemberDto;
import code.hub.ed.team1.model.CrewMember;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CrewMemberMapper {

  CrewMember crewMemberDtoToCrewMember(CrewMemberDto crewMemberDto);

  CrewMemberDto crewMemberToCrewMemberDto(CrewMember crewMember);
}
