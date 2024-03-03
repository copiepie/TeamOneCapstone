package code.hub.ed.team1.dto;

import code.hub.ed.team1.model.Profession;
import code.hub.ed.team1.model.SalaryType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PeopleDto extends BaseDto {
    protected String name;
    protected SalaryType salaryType;
    protected BigDecimal salary;
    protected Profession profession;
}
