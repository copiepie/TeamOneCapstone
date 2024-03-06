package code.hub.ed.team1.controller;

import code.hub.ed.team1.dto.ActorDto;
import code.hub.ed.team1.dto.PeopleDto;
import code.hub.ed.team1.service.api.ActorService;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/actor")
public class ActorController {


  private ActorService actorService;

  @GetMapping("/{id}")
  public PeopleDto getMovieById(@PathVariable("id") long id) {
    return actorService.read(id);
  }

  @PostMapping("/create")
  public ActorDto createActor(@RequestBody ActorDto actorDto) {
    return actorService.create(actorDto);
  }

  @PutMapping("/update")
  public ActorDto updateActor(@RequestBody ActorDto actorDto) {
    return actorService.update(actorDto);
  }

  @DeleteMapping("/{id}")
  public void deleteActor(@PathVariable long id) {
    actorService.delete(id);
  }
}
