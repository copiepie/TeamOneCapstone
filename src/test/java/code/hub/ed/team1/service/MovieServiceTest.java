package code.hub.ed.team1.service;

import code.hub.ed.team1.exception.MovieNotFoundException;
import code.hub.ed.team1.mapper.MovieMapper;
import code.hub.ed.team1.model.*;
import code.hub.ed.team1.repository.MovieRepository;
import code.hub.ed.team1.service.impl.MovieServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

public class MovieServiceTest {

  @Test
  public void test_calculateCost_movieNotFound_exceptionIsThrown() {
    MovieMapper movieMapper = MovieMapper.INSTANCE;
    MovieRepository movieRepository = Mockito.mock(MovieRepository.class);
    Mockito.when(movieRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

    MovieServiceImpl movieService = new MovieServiceImpl(movieRepository, movieMapper);
    Assertions.assertThatThrownBy(() -> movieService.calculateCost(1l))
        .isInstanceOf(MovieNotFoundException.class)
        .hasMessage("No Movie with id '1' could be found");
  }

  @Test
  public void
      test_calculateCost_movieFound_salariesPerProject_allSalariesAreCalculatedAsExpected() {
    Director stanley =
        Director.builder()
            .salary(BigDecimal.valueOf(1_789_322.99))
            .salaryType(SalaryType.FULL_PROJECT)
            .build();

    Actor malcolm =
        Actor.builder()
            .id(new Random().nextLong())
            .salary(BigDecimal.valueOf(499_573.09))
            .salaryType(SalaryType.FULL_PROJECT)
            .build();

    Actor kirk =
        Actor.builder()
            .id(new Random().nextLong())
            .salary(BigDecimal.valueOf(362_083.76))
            .salaryType(SalaryType.FULL_PROJECT)
            .build();

    Producer chris =
        Producer.builder()
            .id(new Random().nextLong())
            .salary(BigDecimal.valueOf(2_334_099.35))
            .salaryType(SalaryType.FULL_PROJECT)
            .build();

    Producer jennifer =
        Producer.builder()
            .id(new Random().nextLong())
            .salary(BigDecimal.valueOf(5_020_101.83))
            .salaryType(SalaryType.FULL_PROJECT)
            .build();

    CrewMember emilio =
        CrewMember.builder()
            .id(new Random().nextLong())
            .salary(BigDecimal.valueOf(33_982.91))
            .salaryType(SalaryType.FULL_PROJECT)
            .build();

    CrewMember daphne =
        CrewMember.builder()
            .id(new Random().nextLong())
            .salary(BigDecimal.valueOf(99_405.67))
            .salaryType(SalaryType.FULL_PROJECT)
            .build();

    Movie movie =
        Movie.builder()
            .crewMembers(Set.of(daphne, emilio))
            .actors(Set.of(malcolm, kirk))
            .director(stanley)
            .producers(Set.of(jennifer, chris))
            .build();

    MovieMapper movieMapper = MovieMapper.INSTANCE;
    MovieRepository movieRepository = Mockito.mock(MovieRepository.class);
    Mockito.when(movieRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(movie));

    MovieServiceImpl movieService = new MovieServiceImpl(movieRepository, movieMapper);
    BigDecimal totalCost = movieService.calculateCost(1);
    Assertions.assertThat(BigDecimal.valueOf(10_138_569.60))
        .usingComparator(BigDecimal::compareTo)
        .isEqualTo(totalCost);
  }
}
