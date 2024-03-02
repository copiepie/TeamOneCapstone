package code.hub.ed.team1.repository;

import code.hub.ed.team1.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@DataJpaTest
public class MovieRepositoryTest {
  @Autowired private PeopleRepository peopleRepository;

  @Autowired private MovieRepository movieRepository;

  @Test
  public void test_createMovie_movieIsCreatedAndReturnedWhenQueried() {
    Actor benicio =
        Actor.builder()
            .salary(BigDecimal.valueOf(12_329.50))
            .salaryType(SalaryType.SINGLE_EPISODE)
            .name("Benicio del Torro")
            .build();

    Actor denzel =
        Actor.builder()
            .name("Denzel Washington")
            .salary(BigDecimal.valueOf(5_552.99))
            .salaryType(SalaryType.FULL_PROJECT)
            .name("Denzel Washington")
            .build();

    Director martin =
        Director.builder()
            .name("Martin Scorcese")
            .salary(BigDecimal.valueOf(1_309.99))
            .salaryType(SalaryType.FULL_PROJECT)
            .build();

    Producer steven =
        Producer.builder()
            .name("Steven Spielberg")
            .salary(BigDecimal.valueOf(2_098.32))
            .salaryType(SalaryType.FULL_PROJECT)
            .build();

    Producer ridley =
        Producer.builder()
            .name("Ridley Scott")
            .salary(BigDecimal.valueOf(9_088.21))
            .salaryType(SalaryType.FULL_PROJECT)
            .build();

    CrewMember kostas =
        CrewMember.builder()
            .name("Kostas Perperidis")
            .salary(BigDecimal.valueOf(509.99))
            .salaryType(SalaryType.FULL_PROJECT)
            .build();

    CrewMember fotis =
        CrewMember.builder()
            .name("Fotis Felekidis")
            .salary(BigDecimal.valueOf(780.99))
            .salaryType(SalaryType.FULL_PROJECT)
            .build();

    benicio = peopleRepository.save(benicio);
    denzel = peopleRepository.save(denzel);
    ridley = peopleRepository.save(ridley);
    steven = peopleRepository.save(steven);
    martin = peopleRepository.save(martin);
    fotis = peopleRepository.save(fotis);
    kostas = peopleRepository.save(kostas);

    Movie movie =
        Movie.builder()
            .actors(Set.of(benicio, denzel))
            .crewMembers(Set.of(kostas, fotis))
            .producers(Set.of(steven, ridley))
            .director(martin)
            .genre(Genre.DRAMA)
            .productionBudget(BigDecimal.valueOf(198_302.09))
            .yearOfRelease(2024)
            .build();

    movie = movieRepository.save(movie);
    Assertions.assertNotNull(movie.getId());

    List<Movie> movies = movieRepository.findAll();
    Assertions.assertNotNull(movies);
    Assertions.assertFalse(movies.isEmpty());

    movie = movies.get(0);

    Assertions.assertEquals(BigDecimal.valueOf(198_302.09), movie.getProductionBudget());
    Assertions.assertEquals(2024, movie.getYearOfRelease());
    Assertions.assertEquals(
        Set.of("Kostas Perperidis", "Fotis Felekidis"),
        movie.getCrewMembers().stream().map(CrewMember::getName).collect(Collectors.toSet()));
    Assertions.assertEquals(
        Set.of("Benicio del Torro", "Denzel Washington"),
        movie.getActors().stream().map(Actor::getName).collect(Collectors.toSet()));
    Assertions.assertEquals("Martin Scorcese", movie.getDirector().getName());
    Assertions.assertEquals(
        Set.of("Steven Spielberg", "Ridley Scott"),
        movie.getProducers().stream().map(Producer::getName).collect(Collectors.toSet()));
  }
}
