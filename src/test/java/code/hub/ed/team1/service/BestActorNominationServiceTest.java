package code.hub.ed.team1.service;

import code.hub.ed.team1.dto.BestActorNominationDto;
import code.hub.ed.team1.model.Category;
import code.hub.ed.team1.model.Genre;
import code.hub.ed.team1.model.NominationResult;
import code.hub.ed.team1.model.SalaryType;
import code.hub.ed.team1.repository.ActorRepository;
import code.hub.ed.team1.repository.BestActorNominationRepository;
import code.hub.ed.team1.repository.DirectoryRepository;
import code.hub.ed.team1.repository.MovieRepository;
import code.hub.ed.team1.service.api.BestActorNominationService;
import code.hub.ed.team1.util.NominationTestData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootTest
public class BestActorNominationServiceTest {

  @Autowired private BestActorNominationService bestActorNominationService;

  @Autowired private ActorRepository actorRepository;

  @Autowired private BestActorNominationRepository bestActorNominationRepository;

  @Autowired private DirectoryRepository directoryRepository;

  @Autowired private MovieRepository movieRepository;

  @Test
  public void test_getBestActorNominationsForYearsRange() {
    Map<Integer, Set<BestActorNominationDto>> bestActorNominations =
        bestActorNominationService.getBestActorNominationsForYearsRange(1950, 1970);

    Assertions.assertThat(bestActorNominations.size()).isEqualTo(3);
    Assertions.assertThat(bestActorNominations.keySet()).isEqualTo(Set.of(1962, 1967, 1965));
    Assertions.assertThat(
            bestActorNominations.get(1962).stream()
                .map(dto -> dto.getActor())
                .collect(Collectors.toSet()))
        .isEqualTo(Set.of("James Steward"));
    Assertions.assertThat(
            bestActorNominations.get(1967).stream()
                .map(dto -> dto.getActor())
                .collect(Collectors.toSet()))
        .isEqualTo(Set.of("Anthony Perkins"));
    Assertions.assertThat(
            bestActorNominations.get(1965).stream()
                .map(dto -> dto.getActor())
                .collect(Collectors.toSet()))
        .isEqualTo(Set.of("Shirley McLane"));
  }

  @BeforeEach
  public void beforeEach() {
    NominationTestData.Builder builder =
        new NominationTestData.Builder(
            actorRepository, movieRepository, bestActorNominationRepository, directoryRepository);
    builder
        .makeMovie()
        .withTitle("Vertigo")
        .withGenre(Genre.THRILLER)
        .withProductionBudget(BigDecimal.valueOf(1_000_000))
        .withYearOfRelease(1960)
        .withDirector()
        .andActor()
        .withName("James Steward")
        .withSalary(BigDecimal.valueOf(100_000))
        .withSalaryType(SalaryType.FULL_PROJECT)
        .buildMovieAndActor()
        .andNomination()
        .addNominee()
        .inGenre(Genre.THRILLER)
        .withNominationYear(1962)
        .withNominationResult(NominationResult.WON)
        .withCategory(Category.BEST_MALE_ROLE)
        .withMovie()
        .build();

    builder
        .makeMovie()
        .withTitle("Psycho")
        .withGenre(Genre.THRILLER)
        .withProductionBudget(BigDecimal.valueOf(1_000_000))
        .withYearOfRelease(1962)
        .withDirector()
        .andActor()
        .withName("Anthony Perkins")
        .withSalary(BigDecimal.valueOf(100_000))
        .withSalaryType(SalaryType.FULL_PROJECT)
        .buildMovieAndActor()
        .andNomination()
        .addNominee()
        .inGenre(Genre.HORROR)
        .withNominationYear(1967)
        .withNominationResult(NominationResult.WON)
        .withCategory(Category.BEST_VILLAIN)
        .withMovie()
        .build();

    builder
        .makeMovie()
        .withTitle("The man who knew too much")
        .withGenre(Genre.THRILLER)
        .withProductionBudget(BigDecimal.valueOf(1_000_000))
        .withYearOfRelease(1965)
        .withDirector()
        .andActor()
        .withName("Shirley McLane")
        .withSalary(BigDecimal.valueOf(100_000))
        .withSalaryType(SalaryType.FULL_PROJECT)
        .buildMovieAndActor()
        .andNomination()
        .addNominee()
        .inGenre(Genre.THRILLER)
        .withNominationYear(1965)
        .withNominationResult(NominationResult.WON)
        .withCategory(Category.BEST_FEMALE_ROLE)
        .withMovie()
        .build();

    builder
        .makeMovie()
        .withTitle("One flew over the Cuckoos nest")
        .withGenre(Genre.THRILLER)
        .withProductionBudget(BigDecimal.valueOf(1_000_000))
        .withYearOfRelease(1983)
        .withDirector()
        .andActor()
        .withName("Jack Nicholson")
        .withSalary(BigDecimal.valueOf(100_000))
        .withSalaryType(SalaryType.FULL_PROJECT)
        .buildMovieAndActor()
        .andNomination()
        .addNominee()
        .inGenre(Genre.THRILLER)
        .withNominationYear(1984)
        .withNominationResult(NominationResult.WON)
        .withCategory(Category.BEST_HERO)
        .withMovie()
        .build();
  }
}
