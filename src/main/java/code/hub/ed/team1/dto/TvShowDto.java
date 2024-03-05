package code.hub.ed.team1.dto;

import code.hub.ed.team1.model.Genre;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class TvShowDto extends BaseDto {
    
    private String title;
    
    private DirectorDto director;
    
    private Set<ActorDto> actors;
    
    private Set<ProducerDto> producers;
    
    private Set<CrewMemberDto> crewMembers;
    
    private Set<PeopleDto> people;
    
    private Genre genre;
    
    private BigDecimal minBudgetPerEpisode;
    
    private BigDecimal maxBudgetPerEpisode;
    
    private Integer startingYear;
    
    private Integer endingYear;
    
    private Integer numberOfEpisodes;
}

