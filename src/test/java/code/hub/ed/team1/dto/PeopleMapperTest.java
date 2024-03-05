package code.hub.ed.team1.dto;

import code.hub.ed.team1.mapper.PeopleMapper;
import code.hub.ed.team1.model.Actor;
import code.hub.ed.team1.model.Profession;
import code.hub.ed.team1.model.SalaryType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class PeopleMapperTest {

  @Test
  public void test_mapPeopleDtoToActor_yieldsAnActor() {
    PeopleDto peopleDto = new PeopleDto();
    peopleDto.setProfession(Profession.ACTOR);
    peopleDto.setName("Thanassis Beggos");
    peopleDto.setSalary(BigDecimal.ONE);
    peopleDto.setSalaryType(SalaryType.FULL_PROJECT);

    Actor people = PeopleMapper.INSTANCE.peopleDtoToActor(peopleDto);
    Assertions.assertTrue(people instanceof Actor);
    Assertions.assertEquals("Thanassis Beggos", people.getName());
  }
}
