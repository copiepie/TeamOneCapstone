package code.hub.ed.team1.controller;

import code.hub.ed.team1.dto.CrewMemberDto;
import code.hub.ed.team1.service.api.CrewMemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crew-members")
public class CrewMemberController {
	
	private final CrewMemberService crewMemberService;
	
	public CrewMemberController(CrewMemberService crewMemberService) {
		this.crewMemberService = crewMemberService;
	}
	
	@GetMapping
	public List<CrewMemberDto> getAllCrewMembers() {
		return crewMemberService.findAllCrewMembers();
	}
	
	@GetMapping("/{id}")
	public CrewMemberDto getCrewMemberById(@PathVariable Long id) {
		return crewMemberService.read(id);
	}
	
	@PostMapping
	public CrewMemberDto createCrewMember(@RequestBody CrewMemberDto crewMemberDto) {
		return crewMemberService.create(crewMemberDto);
	}
	
	@PutMapping("/{id}")
	public CrewMemberDto updateCrewMember(@PathVariable Long id, @RequestBody CrewMemberDto crewMemberDto) {
		return crewMemberService.update(id, crewMemberDto);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCrewMember(@PathVariable Long id) {
		crewMemberService.delete(id);
	}
}
