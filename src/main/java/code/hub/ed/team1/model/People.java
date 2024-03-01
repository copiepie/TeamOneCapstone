package code.hub.ed.team1.model;

import jakarta.persistence.Entity;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class People extends BaseEntity {

  private String name;

  private Profession profession;

  private SalaryType salaryType;

  private BigDecimal salary;
}
