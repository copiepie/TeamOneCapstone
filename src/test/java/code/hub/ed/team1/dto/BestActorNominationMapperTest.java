package code.hub.ed.team1.dto;

import code.hub.ed.team1.mapper.BestActorNominationMapper;
import code.hub.ed.team1.model.Actor;
import code.hub.ed.team1.model.BestActorNomination;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BestActorNominationMapperTest {

  @Test
  public void test_mapNominationToDto() {
    Actor actor = Actor.builder().name("Robert Downey Jr.").id(1l).build();
    BestActorNomination bestActorNomination = BestActorNomination.builder().actor(actor).build();

    BestActorNominationMapper mapper = BestActorNominationMapper.INSTANCE;

    BestActorNominationDto bestActorNominationDto = mapper.toDto(bestActorNomination);
    Assertions.assertEquals(1l, bestActorNominationDto.getActorId());
    Assertions.assertEquals("Robert Downey Jr.", bestActorNominationDto.getActor());
  }

  @Test
  public void test_mapNominationDtoToEntity() {
    BestActorNominationDto bestActorNominationDto = new BestActorNominationDto();
    bestActorNominationDto.setActor("Elijah Wood");
    bestActorNominationDto.setActorId(2l);

    Actor actor = Actor.builder().id(2l).build();

    BestActorNominationMapper mapper = BestActorNominationMapper.INSTANCE;

    BestActorNomination bestActorNomination = mapper.toEntity(bestActorNominationDto);
    Assertions.assertEquals(2l, bestActorNomination.getActor().getId());
    Assertions.assertEquals(actor, bestActorNomination.getActor());
  }
}
