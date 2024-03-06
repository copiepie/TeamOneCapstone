package code.hub.ed.team1.service;

import code.hub.ed.team1.dto.*;
import code.hub.ed.team1.model.*;
import code.hub.ed.team1.repository.*;
import code.hub.ed.team1.service.api.BestActorNominationService;
import code.hub.ed.team1.service.api.MovieService;
import code.hub.ed.team1.util.NominationTestData;
import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest
@TestPropertySource("/test-application.properties")
public class BestActorNominationServiceTest {

  @Autowired private BestActorNominationService bestActorNominationService;

  @Autowired private ActorRepository actorRepository;

  @Autowired private BestActorNominationRepository bestActorNominationRepository;

  @Autowired private DirectoryRepository directoryRepository;

  @Autowired private MovieRepository movieRepository;

  @Autowired private PeopleRepository peopleRepository;

  @Autowired private MovieService movieService;

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
    Queue<DirectorDto> directors = makeDirectors(3);
    Queue<ProducerDto> producers = makeProducers(3);
    Queue<CrewMemberDto> crewMembers = makeCrewMembers(3);

    MovieDto theGoodBadAndUgly = new MovieDto();
    theGoodBadAndUgly.setGenre(Genre.DRAMA);
    theGoodBadAndUgly.setTitle("The good, the bad and the ugly");
    theGoodBadAndUgly.setProductionBudget(BigDecimal.ONE);
    theGoodBadAndUgly.setYearOfRelease(1971);
    theGoodBadAndUgly.setDirector(directors.poll());
    theGoodBadAndUgly.setProducers(Set.of(producers.poll()));
    theGoodBadAndUgly.setCrewMembers(Set.of(crewMembers.poll()));
    ActorDto lee = new ActorDto();
    lee.setName("Lee van Cliff");
    lee.setProfession(Profession.ACTOR);
    lee.setSalary(BigDecimal.ONE);
    lee.setSalaryType(SalaryType.FULL_PROJECT);
    theGoodBadAndUgly.setActors(Set.of(lee));

    theGoodBadAndUgly = movieService.create(theGoodBadAndUgly);
    lee.setId(theGoodBadAndUgly.getActors().iterator().next().getId());

    MovieDto forHandfulOfDollars = new MovieDto();
    forHandfulOfDollars.setGenre(Genre.DRAMA);
    forHandfulOfDollars.setTitle("For a handful of dollars");
    forHandfulOfDollars.setProductionBudget(BigDecimal.ONE);
    forHandfulOfDollars.setYearOfRelease(1972);
    forHandfulOfDollars.setDirector(directors.poll());
    forHandfulOfDollars.setProducers(Set.of(producers.poll()));
    forHandfulOfDollars.setCrewMembers(Set.of(crewMembers.poll()));
    forHandfulOfDollars.setActors(Set.of(lee));

    forHandfulOfDollars = movieService.create(forHandfulOfDollars);

    MovieDto forFewDollarsMore = new MovieDto();
    forFewDollarsMore.setGenre(Genre.DRAMA);
    forFewDollarsMore.setTitle("The good, the bad and the ugly");
    forFewDollarsMore.setProductionBudget(BigDecimal.ONE);
    forFewDollarsMore.setYearOfRelease(1973);
    forFewDollarsMore.setDirector(directors.poll());
    forFewDollarsMore.setProducers(Set.of(producers.poll()));
    forFewDollarsMore.setCrewMembers(Set.of(crewMembers.poll()));
    forFewDollarsMore.setActors(Set.of(lee));
    ActorDto clint = new ActorDto();
    clint.setName("Clint Eastwood");
    clint.setProfession(Profession.ACTOR);
    clint.setSalary(BigDecimal.ONE);
    clint.setSalaryType(SalaryType.FULL_PROJECT);
    forFewDollarsMore.setActors(Set.of(clint));

    forFewDollarsMore = movieService.create(forFewDollarsMore);

    BestActorNominationDto nominationOne = new BestActorNominationDto();
    nominationOne.setActor(lee.getName());
    nominationOne.setActorId(theGoodBadAndUgly.getActors().iterator().next().getId());
    nominationOne.setMovie(theGoodBadAndUgly.getTitle());
    nominationOne.setMovieId(theGoodBadAndUgly.getId());
    nominationOne.setNominationResult(NominationResult.NOMINATED);
    nominationOne.setCategory(Category.BEST_MALE_ROLE);
    nominationOne.setGenre(Genre.DRAMA);
    nominationOne.setNominationYear(1971);

    BestActorNominationDto nominationTwo = new BestActorNominationDto();
    nominationTwo.setActor(lee.getName());
    nominationTwo.setActorId(forHandfulOfDollars.getActors().iterator().next().getId());
    nominationTwo.setMovie(forHandfulOfDollars.getTitle());
    nominationTwo.setMovieId(forHandfulOfDollars.getId());
    nominationTwo.setNominationResult(NominationResult.NOMINATED);
    nominationTwo.setCategory(Category.BEST_HERO);
    nominationTwo.setGenre(Genre.DRAMA);
    nominationTwo.setNominationYear(1972);

    BestActorNominationDto nominationThree = new BestActorNominationDto();
    nominationThree.setActor(clint.getName());
    nominationThree.setActorId(forFewDollarsMore.getActors().iterator().next().getId());
    nominationThree.setMovie(forFewDollarsMore.getTitle());
    nominationThree.setMovieId(forFewDollarsMore.getId());
    nominationThree.setNominationResult(NominationResult.NOMINATED);
    nominationThree.setCategory(Category.BEST_MALE_ROLE);
    nominationThree.setGenre(Genre.DRAMA);
    nominationThree.setNominationYear(1973);

    bestActorNominationService.create(nominationOne);
    bestActorNominationService.create(nominationTwo);
    bestActorNominationService.create(nominationThree);

    Set<BestActorNominationDto> atLeastOnceNominated = bestActorNominationService.getByMinTimesNominated(1);
    Assertions.assertThat(atLeastOnceNominated.size()).isEqualTo(3);


  }

  private Queue<DirectorDto> makeDirectors(int cnt) {
    Lorem lorem = LoremIpsum.getInstance();
    Queue<DirectorDto> directorDtos = new ArrayDeque<>();
    IntStream.rangeClosed(1, cnt)
        .forEach(
            i -> {
              DirectorDto dto = new DirectorDto();
              dto.setName(lorem.getName());
              dto.setProfession(Profession.DIRECTOR);
              dto.setSalary(BigDecimal.ONE);
              dto.setSalaryType(SalaryType.FULL_PROJECT);
              directorDtos.add(dto);
            });
    return directorDtos;
  }

  private Queue<ProducerDto> makeProducers(int cnt) {
    Lorem lorem = LoremIpsum.getInstance();
    Queue<ProducerDto> producerDtos = new ArrayDeque<>();
    IntStream.rangeClosed(1, cnt)
        .forEach(
            i -> {
              ProducerDto dto = new ProducerDto();
              dto.setName(lorem.getName());
              dto.setProfession(Profession.PRODUCER);
              dto.setSalary(BigDecimal.ONE);
              dto.setSalaryType(SalaryType.FULL_PROJECT);
              producerDtos.add(dto);
            });
    return producerDtos;
  }

  private Queue<CrewMemberDto> makeCrewMembers(int cnt) {
    Lorem lorem = LoremIpsum.getInstance();
    Queue<CrewMemberDto> crewMemberDtos = new ArrayDeque<>();
    IntStream.rangeClosed(1, cnt)
        .forEach(
            i -> {
              CrewMemberDto dto = new CrewMemberDto();
              dto.setName(lorem.getName());
              dto.setProfession(Profession.CREW_MEMBER);
              dto.setSalary(BigDecimal.ONE);
              dto.setSalaryType(SalaryType.FULL_PROJECT);
              crewMemberDtos.add(dto);
            });
    return crewMemberDtos;
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
