package code.hub.ed.team1.repository;

import code.hub.ed.team1.dto.BestActorNominationDto;
import code.hub.ed.team1.mapper.BestActorNominationMapper;
import code.hub.ed.team1.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.Set;

@DataJpaTest
public class BestActorNominationRepositoryTest {
  @Autowired private BestActorNominationRepository bestActorNominationRepository;

  @Autowired private ActorRepository actorRepository;

  @Autowired private PeopleRepository peopleRepository;

  @Autowired private MovieRepository movieRepository;

  @Test
  public void test_updateBestActorNomination_mergeDoesNotCascadeToActor() {
    Actor kinski =
        Actor.builder()
            .name("Klaus Kinski")
            .salaryType(SalaryType.FULL_PROJECT)
            .salary(BigDecimal.ONE)
            .build();

    kinski = actorRepository.save(kinski);

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
    Director martin =
        Director.builder()
            .name("Martin Scorcese")
            .salary(BigDecimal.valueOf(1_309.99))
            .salaryType(SalaryType.FULL_PROJECT)
            .build();
    ridley = peopleRepository.save(ridley);
    steven = peopleRepository.save(steven);
    martin = peopleRepository.save(martin);
    fotis = peopleRepository.save(fotis);
    kostas = peopleRepository.save(kostas);

    Movie movie =
        Movie.builder()
            .actors(Set.of(kinski))
            .crewMembers(Set.of(kostas, fotis))
            .producers(Set.of(steven, ridley))
            .director(martin)
            .genre(Genre.DRAMA)
            .productionBudget(BigDecimal.valueOf(198_302.09))
            .yearOfRelease(2024)
            .build();

    movie = movieRepository.save(movie);

    BestActorNomination bestActorNomination =
        BestActorNomination.builder()
            .actor(kinski)
            .nominationResult(NominationResult.NOMINATED)
            .nominationYear(1981)
            .movie(movie)
            .genre(Genre.THRILLER)
            .category(Category.BEST_MALE_ROLE)
            .build();

    bestActorNomination = bestActorNominationRepository.save(bestActorNomination);

    BestActorNominationMapper mapper = BestActorNominationMapper.INSTANCE;

    BestActorNominationDto dto = mapper.toDto(bestActorNomination);
    bestActorNomination = mapper.toEntity(dto);

    bestActorNominationRepository.save(bestActorNomination);
    Actor kinski2 = actorRepository.findById(kinski.getId()).get();
    Assertions.assertEquals("Klaus Kinski", kinski2.getName());
    Assertions.assertEquals(BigDecimal.ONE, kinski2.getSalary());
    Assertions.assertEquals(SalaryType.FULL_PROJECT, kinski2.getSalaryType());
  }
}
