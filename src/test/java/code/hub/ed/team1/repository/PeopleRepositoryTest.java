package code.hub.ed.team1.repository;

import code.hub.ed.team1.model.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@DataJpaTest
public class PeopleRepositoryTest {

  @Autowired private PeopleRepository peopleRepository;

  @Autowired private MovieRepository movieRepository;

  @Autowired private TvShowRepository tvShowRepository;

  @Autowired private ActorRepository actorRepository;

  @Test
  public void test_getPeopleRecordForActor_allMoviesAndShowsAreIncluded() {
    Actor robin =
        Actor.builder()
            .salary(BigDecimal.valueOf(12_329.50))
            .salaryType(SalaryType.SINGLE_EPISODE)
            .name("Robin Williams")
            .build();
    Director chris =
        Director.builder()
            .name("Chris Columbus")
            .salary(BigDecimal.valueOf(12_329.50))
            .salaryType(SalaryType.SINGLE_EPISODE)
            .build();

    Producer mark =
        Producer.builder()
            .name("Mark Frankenstein")
            .salary(BigDecimal.valueOf(12_329.50))
            .salaryType(SalaryType.SINGLE_EPISODE)
            .build();

    CrewMember donnie =
        CrewMember.builder()
            .name("Donnie Wahlberg")
            .salary(BigDecimal.valueOf(12_329.50))
            .salaryType(SalaryType.SINGLE_EPISODE)
            .build();

    mark = peopleRepository.save(mark);
    donnie = peopleRepository.save(donnie);
    robin = peopleRepository.save(robin);
    chris = peopleRepository.save(chris);

    Movie mrsDoubtfire =
        Movie.builder()
            .title("Mrs. Doubtfire")
            .actors(Set.of(robin))
            .director(chris)
            .crewMembers(Set.of(donnie))
            .producers(Set.of(mark))
            .genre(Genre.COMEDY)
            .productionBudget(BigDecimal.valueOf(198_302.09))
            .yearOfRelease(2024)
            .build();

    TvShow morkFromOrk =
        TvShow.builder()
            .title("Mork from Ork")
            .actors(Set.of(robin))
            .director(chris)
            .crewMembers(Set.of(donnie))
            .producers(Set.of(mark))
            .genre(Genre.COMEDY)
            .minBudgetPerEpisode(BigDecimal.valueOf(198_302.09))
            .maxBudgetPerEpisode(BigDecimal.valueOf(500_000))
            .startingYear(1970)
            .endingYear(1981)
            .numberOfEpisodes(1000)
            .genre(Genre.COMEDY)
            .build();

    movieRepository.save(mrsDoubtfire);
    tvShowRepository.save(morkFromOrk);

    robin.addMovies(mrsDoubtfire);
    mark.addMovies(mrsDoubtfire);
    chris.addMovies(mrsDoubtfire);
    donnie.addMovies(mrsDoubtfire);

    actorRepository.save(robin);

    List<People> allPeople = peopleRepository.findAll();
    Assertions.assertThat(List.of(donnie, mark, robin, chris)).hasSameElementsAs(allPeople);

    Optional<Actor> optActor =
        allPeople.stream().filter(p -> p instanceof Actor).map(Actor.class::cast).findFirst();
    Assertions.assertThat(optActor.get().getMovies()).contains(mrsDoubtfire);

    Optional<Director> optDirector =
        allPeople.stream()
            .filter(people -> people instanceof Director)
            .map(Director.class::cast)
            .findFirst();
    Assertions.assertThat(optDirector.get().getMovies()).contains(mrsDoubtfire);

    Optional<Producer> optProducer =
        allPeople.stream()
            .filter(people -> people instanceof Producer)
            .map(Producer.class::cast)
            .findFirst();
    Assertions.assertThat(optProducer.get().getMovies()).contains(mrsDoubtfire);

    Optional<CrewMember> optCrewMember =
        allPeople.stream()
            .filter(people -> people instanceof CrewMember)
            .map(CrewMember.class::cast)
            .findFirst();
    Assertions.assertThat(optCrewMember.get().getMovies()).contains(mrsDoubtfire);
  }
}
