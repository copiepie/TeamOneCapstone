package code.hub.ed.team1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Movie  extends BaseEntity {
  private String title;

  @ManyToOne
  @JoinColumn(name = "director_id", nullable = false)
  private Director director;

  @ManyToMany
  @JoinTable(
    name = "actor_movie",
    joinColumns = @JoinColumn(name = "actor_id"),
    inverseJoinColumns = @JoinColumn(name = "movie_id")
  )
  private Set<Actor> actors;

  @ManyToMany
  @JoinTable(
    name = "producer_movie",
    joinColumns = @JoinColumn(name = "producer_id"),
    inverseJoinColumns = @JoinColumn(name = "movie_id")
  )
  private Set<Producer> producers;

  @ManyToMany
  @JoinTable(
    name = "crew_movie",
    joinColumns = @JoinColumn(name = "crew_id"),
    inverseJoinColumns = @JoinColumn(name = "movie_id")
  )
  private Set<CrewMember> crewMembers;

  @Enumerated
  @Column(nullable = false)
  private Genre genre;

  private BigDecimal productionBudget;

  private LocalDate yearOfRelease;
}
