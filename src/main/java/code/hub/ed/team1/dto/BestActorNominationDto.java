package code.hub.ed.team1.dto;

import code.hub.ed.team1.model.Category;
import code.hub.ed.team1.model.Genre;
import code.hub.ed.team1.model.NominationResult;
import lombok.Data;

@Data
public class BestActorNominationDto extends BaseDto {
    private Integer nominationYear;
    private Genre genre;
    private String actor;
    private long actorId;
    private String movie;
    private long movieId;
    private NominationResult nominationResult;
    private Category category;
}
