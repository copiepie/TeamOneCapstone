package code.hub.ed.team1.dto;

import code.hub.ed.team1.model.Profession;
import code.hub.ed.team1.model.SalaryType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PeopleDto {
    private String name;
    private SalaryType salaryType;
    private BigDecimal salary;
    private Profession profession;
}
