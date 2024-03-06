package code.hub.ed.team1.controller;

import code.hub.ed.team1.dto.BestActorNominationDto;
import code.hub.ed.team1.dto.CrewMemberDto;
import code.hub.ed.team1.service.api.BestActorNominationService;
import code.hub.ed.team1.service.api.PeopleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/bestActorNomination")
public class BestActorNominationController {

    private final BestActorNominationService bestActorNominationService;

    @GetMapping("/{id}")
    public BestActorNominationDto getBestActorNomination(@PathVariable("id") long id) {
        return bestActorNominationService.read(id);
    }

    @PostMapping
    public BestActorNominationDto createBestActorNomination(BestActorNominationDto bestActorNominationDto) {
        return bestActorNominationService.create(bestActorNominationDto);
    }

    @PutMapping
    public BestActorNominationDto updateBestActorNomination(BestActorNominationDto bestActorNominationDto) {
        return bestActorNominationService.update(bestActorNominationDto);
    }

    @DeleteMapping("/{id}")
    public void deleteBestActorNomination(@PathVariable("id") long id) {
        bestActorNominationService.delete(id);
    }
}
