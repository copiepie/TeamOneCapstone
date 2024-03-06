package code.hub.ed.team1.dto;

import code.hub.ed.team1.model.Profession;
import code.hub.ed.team1.model.SalaryType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@Data
public class PeopleDto extends BaseDto {
    protected String name;
    protected SalaryType salaryType;
    protected BigDecimal salary;
    protected Profession profession;
    protected Set<MovieDto> movies;
    protected Set<TvShowDto> tvShows;
}
