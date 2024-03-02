package code.hub.ed.team1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@SuperBuilder
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "profession")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class People extends BaseEntity {

  @Column(nullable = false)
  private String name;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private SalaryType salaryType;

  @Column(nullable = false)
  private BigDecimal salary;
}
