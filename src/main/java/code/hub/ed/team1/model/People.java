package code.hub.ed.team1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@DiscriminatorColumn(
  discriminatorType = DiscriminatorType.STRING,
  name = "profession"
)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class People extends BaseEntity {

  protected String name;

  @Enumerated(EnumType.STRING)
  protected SalaryType salaryType;

  protected BigDecimal salary;

  @ManyToOne
  @JoinColumn(name = "tvShow_id")
  protected TvShow tvShow;
}
