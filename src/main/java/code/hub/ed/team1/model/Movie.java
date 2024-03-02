package code.hub.ed.team1.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Movie extends BaseEntity {
  private String title;

  @ManyToOne
  @JoinColumn(name = "director_id", nullable = false)
  private Director director;

  @ManyToMany
  @JoinTable(
      name = "actor_movie",
      joinColumns = @JoinColumn(name = "movie_id"),
      inverseJoinColumns = @JoinColumn(name = "actor_id"))
  private Set<Actor> actors;

  @ManyToMany
  @JoinTable(
      name = "producer_movie",
      joinColumns = @JoinColumn(name = "movie_id"),
      inverseJoinColumns = @JoinColumn(name = "producer_id"))
  private Set<Producer> producers;

  @ManyToMany
  @JoinTable(
      name = "crew_movie",
      joinColumns = @JoinColumn(name = "movie_id"),
      inverseJoinColumns = @JoinColumn(name = "crew_id"))
  private Set<CrewMember> crewMembers;

  @Enumerated
  @Column(nullable = false)
  private Genre genre;

  @Column(nullable = false)
  private BigDecimal productionBudget;

  @Column(nullable = false)
  private Integer yearOfRelease;
}
