package code.hub.ed.team1.controller;

import code.hub.ed.team1.dto.MovieDto;
import code.hub.ed.team1.service.api.MovieService;
import jakarta.websocket.server.PathParam;
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
@RequestMapping("/movie")
public class MovieController {

  private final MovieService movieService;

  @GetMapping("/{id}")
  public MovieDto getMovieById(@PathVariable("id") long id) {
    return movieService.read(id);
  }

  @PostMapping("/create")
  public MovieDto createMovie(@RequestBody MovieDto movieDto) {
    return movieService.create(movieDto);
  }

  @PutMapping("/update")
  public MovieDto updateMovie(@RequestBody MovieDto movieDto) {
    return movieService.update(movieDto);
  }

  @DeleteMapping("/{id}")
  public void deleteMovie(@PathVariable long id) {
    movieService.delete(id);
  }
}
