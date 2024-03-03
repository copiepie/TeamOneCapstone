package code.hub.ed.team1.controller;

import code.hub.ed.team1.dto.MovieDto;
import code.hub.ed.team1.service.api.MovieService;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}
