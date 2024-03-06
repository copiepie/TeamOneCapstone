package code.hub.ed.team1.controller;

import code.hub.ed.team1.dto.ActorDto;
import code.hub.ed.team1.dto.PeopleDto;
import code.hub.ed.team1.service.api.PeopleService;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/actor")
public class ActorController {


  private PeopleService actorService;

  @GetMapping("/{id}")
  public PeopleDto getMovieById(@PathVariable("id") long id) {
    return actorService.read(id);
  }

}
