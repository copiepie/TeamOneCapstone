package code.hub.ed.team1.dto;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import code.hub.ed.team1.model.BaseEntity;
import lombok.Data;

@Data
public class MovieDto extends BaseEntity {

    private String title;

    private DirectorDto director;

    private Set<ActorDto> actors;

    private Set<ProducerDto> producers;

    private Set<CrewMemberDto> crewMembers;

    private String genre;

    private BigDecimal productionBudget;

    private LocalDate yearOfRelease;
}
