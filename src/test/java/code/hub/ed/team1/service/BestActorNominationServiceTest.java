package code.hub.ed.team1.service;

import code.hub.ed.team1.dto.BestActorNominationDto;
import code.hub.ed.team1.model.*;
import code.hub.ed.team1.repository.*;
import code.hub.ed.team1.service.api.BestActorNominationService;
import code.hub.ed.team1.util.NominationTestData;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootTest
@TestPropertySource("/test-application.properties")
public class BestActorNominationServiceTest {

  @Autowired private BestActorNominationService bestActorNominationService;

  @Autowired private ActorRepository actorRepository;

  @Autowired private BestActorNominationRepository bestActorNominationRepository;

  @Autowired private DirectoryRepository directoryRepository;

  @Autowired private MovieRepository movieRepository;

  @Autowired private PeopleRepository peopleRepository;

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

    bestActorNominations =
        bestActorNominationService.getBestActorNominationsForYearsRange(1970, 2000);
    Assertions.assertThat(bestActorNominations.size()).isEqualTo(1);
    Assertions.assertThat(bestActorNominations.keySet()).isEqualTo(Set.of(1984));
  }

  @Test
  public void test_getByMinTimesNominated() {
    Actor lee =
        Actor.builder()
            .salary(BigDecimal.ONE)
            .salaryType(SalaryType.FULL_PROJECT)
            .name("Lee Van Cliff")
            .build();

    Actor clint =
        Actor.builder()
            .salary(BigDecimal.ONE)
            .salaryType(SalaryType.FULL_PROJECT)
            .name("Lee Van Cliff")
            .build();

    lee = peopleRepository.save(lee);
    clint = peopleRepository.save(clint);

    Movie theGoodBadAndUgly =
        Movie.builder()
            .crewMembers(
                Set.of(
                    CrewMember.builder()
                        .salary(BigDecimal.ONE)
                        .salaryType(SalaryType.FULL_PROJECT)
                        .name("Charlotte")
                        .build()))
            .actors(Set.of(lee))
            .director(
                Director.builder()
                    .salary(BigDecimal.ONE)
                    .salaryType(SalaryType.FULL_PROJECT)
                    .name("Sergio Leone")
                    .build())
            .producers(
                Set.of(
                    Producer.builder()
                        .salary(BigDecimal.ONE)
                        .salaryType(SalaryType.FULL_PROJECT)
                        .name("Enio Moriccone")
                        .build()))
            .title("The Good, the bad and the ugly")
            .genre(Genre.DRAMA)
            .productionBudget(BigDecimal.ONE)
            .yearOfRelease(1972)
            .build();

    Movie forHandfulOfDollars =
        Movie.builder()
            .crewMembers(
                Set.of(
                    CrewMember.builder()
                        .salary(BigDecimal.ONE)
                        .salaryType(SalaryType.FULL_PROJECT)
                        .name("Charlotte")
                        .build()))
            .actors(Set.of(lee))
            .director(
                Director.builder()
                    .salary(BigDecimal.ONE)
                    .salaryType(SalaryType.FULL_PROJECT)
                    .name("Sergio Leone")
                    .build())
            .producers(
                Set.of(
                    Producer.builder()
                        .salary(BigDecimal.ONE)
                        .salaryType(SalaryType.FULL_PROJECT)
                        .name("Enio Moriccone")
                        .build()))
            .title("The Good, the bad and the ugly")
            .genre(Genre.DRAMA)
            .productionBudget(BigDecimal.ONE)
            .yearOfRelease(1973)
            .build();

    Movie forFewDollarsMore =
        Movie.builder()
            .crewMembers(
                Set.of(
                    CrewMember.builder()
                        .salary(BigDecimal.ONE)
                        .salaryType(SalaryType.FULL_PROJECT)
                        .name("Charlotte")
                        .build()))
            .actors(Set.of(clint))
            .director(
                Director.builder()
                    .salary(BigDecimal.ONE)
                    .salaryType(SalaryType.FULL_PROJECT)
                    .name("Sergio Leone")
                    .build())
            .producers(
                Set.of(
                    Producer.builder()
                        .salary(BigDecimal.ONE)
                        .salaryType(SalaryType.FULL_PROJECT)
                        .name("Enio Moriccone")
                        .build()))
            .title("The Good, the bad and the ugly")
            .genre(Genre.DRAMA)
            .productionBudget(BigDecimal.ONE)
            .yearOfRelease(1972)
            .build();

    theGoodBadAndUgly = movieRepository.save(theGoodBadAndUgly);
    forHandfulOfDollars = movieRepository.save(forHandfulOfDollars);
    forFewDollarsMore = movieRepository.save(forFewDollarsMore);

    BestActorNomination lvc1 =
        BestActorNomination.builder()
            .actor(lee)
            .movie(theGoodBadAndUgly)
            .category(Category.BEST_MALE_ROLE)
            .genre(Genre.DRAMA)
            .nominationResult(NominationResult.NOMINATED)
            .nominationYear(1989)
            .build();

    BestActorNomination lvc2 =
        BestActorNomination.builder()
            .actor(lee)
            .movie(forHandfulOfDollars)
            .category(Category.BEST_HERO)
            .genre(Genre.DRAMA)
            .nominationResult(NominationResult.NOMINATED)
            .nominationYear(1986)
            .build();

    BestActorNomination cea =
        BestActorNomination.builder()
            .actor(clint)
            .movie(forFewDollarsMore)
            .category(Category.BEST_SUPPORTING)
            .genre(Genre.DRAMA)
            .nominationResult(NominationResult.NOMINATED)
            .nominationYear(1982)
            .build();

    bestActorNominationRepository.saveAll(List.of(lvc1, lvc2, cea));

    Set<BestActorNominationDto> bestActorNominations =
        bestActorNominationService.getByMinTimesNominated(1);
    Assertions.assertThat(bestActorNominations.size()).isEqualTo(3);

    bestActorNominations = bestActorNominationService.getByMinTimesNominated(2);
    Assertions.assertThat(bestActorNominations.size()).isEqualTo(2);
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
