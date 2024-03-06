package code.hub.ed.team1.controller;

import code.hub.ed.team1.dto.CrewMemberDto;
import code.hub.ed.team1.service.api.CrewMemberService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/crewMember")
public class CrewMemberController {

    private final CrewMemberService crewMemberService;

    @GetMapping("/{id}")
    public CrewMemberDto getCrewMember(@PathVariable("id") long id) {
        return crewMemberService.read(id);
    }

    @PostMapping
    public CrewMemberDto createCrewMember(CrewMemberDto crewMemberDto) {
        return crewMemberService.create(crewMemberDto);
    }

    @PutMapping
    public CrewMemberDto updateCrewMember(CrewMemberDto crewMemberDto) {
        return crewMemberService.update(crewMemberDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCrewMember(@PathVariable("id") long id) {
        crewMemberService.delete(id);
    }
}
