package code.hub.ed.team1.repository;

import code.hub.ed.team1.dto.BestActorNominationDto;
import code.hub.ed.team1.mapper.BestActorNominationMapper;
import code.hub.ed.team1.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

@DataJpaTest
public class BestActorNominationRepositoryTest {
  @Autowired private BestActorNominationRepository bestActorNominationRepository;

  @Autowired private ActorRepository actorRepository;

  @Test
  public void test_updateBestActorNomination_mergeDoesNotCascadeToActor() {
    Actor kinski =
        Actor.builder()
            .name("Klaus Kinski")
            .salaryType(SalaryType.FULL_PROJECT)
            .salary(BigDecimal.ONE)
            .build();

    kinski = actorRepository.save(kinski);

    BestActorNomination bestActorNomination =
        BestActorNomination.builder()
            .actor(kinski)
            .nominationResult(NominationResult.NOMINATED)
            .nominationYear(1981)
            .genre(Genre.THRILLER)
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
