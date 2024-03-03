package code.hub.ed.team1.dto;

import code.hub.ed.team1.model.Genre;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class TvShowDto extends BaseDto {
    DirectorDto director;
    Set<ActorDto> actors;
    Set<ProducerDto> producers;
    Set<CrewMemberDto> crewMembers;
    private BigDecimal minBugdet;
    private BigDecimal maxBugdet;
    private Integer startingYear;
    private Integer endingYear;
    private Integer numberOfEpisodes;
    private String title;
    private Genre genre;
}
