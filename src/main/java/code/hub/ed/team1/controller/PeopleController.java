package code.hub.ed.team1.controller;

import code.hub.ed.team1.dto.PeopleDto;
import code.hub.ed.team1.service.api.PeopleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;

    @GetMapping("/{id}")
    public PeopleDto getPersonById(@PathVariable("id") long id) {
        return peopleService.findByIdWithMoviesAndTvShows(id);
    }

    @GetMapping("/{id}")
    public PeopleDto getPeople(@PathVariable("id") long id){
        return peopleService.read(id);
    }

    @PostMapping
    public PeopleDto createPeople(@RequestBody PeopleDto peopleDto) {
        return peopleService.create(peopleDto);
    }

    @PutMapping
    public PeopleDto updatePeople(PeopleDto peopleDto){
        return peopleService.update(peopleDto);
    }

    @DeleteMapping("/{id}")
    public void deletePeople(@PathVariable("id") long id){
    peopleService.delete(id);
    }
}
