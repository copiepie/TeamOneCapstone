package code.hub.ed.team1.dto;

import code.hub.ed.team1.model.Genre;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class MovieDto extends BaseDto {

  private String title;

  private DirectorDto director;

  private Set<ActorDto> actors;

  private Set<ProducerDto> producers;

  private Set<CrewMemberDto> crewMembers;

  private Set<PeopleDto> people;

  private Genre genre;

  private BigDecimal productionBudget;

  private Integer yearOfRelease;
}
