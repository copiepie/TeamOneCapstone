package code.hub.ed.team1.dto;

import code.hub.ed.team1.model.Genre;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
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
