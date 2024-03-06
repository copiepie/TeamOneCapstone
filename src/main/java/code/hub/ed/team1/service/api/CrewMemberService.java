package code.hub.ed.team1.service.api;

import code.hub.ed.team1.dto.CrewMemberDto;

import java.util.List;

public interface CrewMemberService extends GeneralService<CrewMemberDto, Long> {
	
	List<CrewMemberDto> findAllCrewMembers();
}
