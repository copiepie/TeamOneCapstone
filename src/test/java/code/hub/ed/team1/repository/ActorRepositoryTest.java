package code.hub.ed.team1.repository;

import code.hub.ed.team1.model.Actor;
import code.hub.ed.team1.model.SalaryType;
import code.hub.ed.team1.repository.ActorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;

@DataJpaTest
public class ActorRepositoryTest {

  @Autowired private ActorRepository actorRepository;
  @Autowired private PeopleRepository peopleRepository;

  @Test
  public void test_createActor_anActorGetsCreated() {
    Actor actor =
        Actor.builder()
            .salary(BigDecimal.valueOf(10_000))
            .salaryType(SalaryType.SINGLE_EPISODE)
            .name("Benicio del Torro")
            .build();

    Actor savedActor = actorRepository.save(actor);
    Assertions.assertNotNull(savedActor);
    Assertions.assertNotNull(savedActor.getId());
    Assertions.assertEquals("Benicio del Torro", savedActor.getName());
    Assertions.assertEquals(BigDecimal.valueOf(10_000), savedActor.getSalary());
    Assertions.assertEquals(SalaryType.SINGLE_EPISODE, savedActor.getSalaryType());
  }

  @Test
  public void test_readActor_actorsAreRetrieved() {
    Actor actor =
        Actor.builder()
            .salary(BigDecimal.valueOf(5_000))
            .salaryType(SalaryType.SINGLE_EPISODE)
            .name("William Shattner")
            .build();
    actorRepository.save(actor);

    List<Actor> actors = actorRepository.findAll();
    Assertions.assertEquals(1, actors.size());
    Assertions.assertEquals("William Shattner", actors.get(0).getName());
    Assertions.assertEquals(BigDecimal.valueOf(5_000), actors.get(0).getSalary());
    Assertions.assertEquals(SalaryType.SINGLE_EPISODE, actors.get(0).getSalaryType());
  }

  @Test
  public void test_deletePeopleById_peopleRecordIsDeleted() {
    Actor actor =
            Actor.builder()
                    .salary(BigDecimal.valueOf(5_000))
                    .salaryType(SalaryType.SINGLE_EPISODE)
                    .name("William Shattner")
                    .build();

    actorRepository.save(actor);
    List<Actor> actors = actorRepository.findAll();
    Assertions.assertFalse(actors.isEmpty());

    peopleRepository.deleteById(actor.getId());
    actors = actorRepository.findAll();
    Assertions.assertTrue(actors.isEmpty());
  }
}
