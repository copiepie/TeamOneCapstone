package code.hub.ed.team1.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Movie extends BaseEntity {
  private String title;

  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "director_id", nullable = false)
  private Director director;

  @ManyToMany(cascade = CascadeType.PERSIST)
  @JoinTable(
      name = "actor_movie",
      joinColumns = @JoinColumn(name = "movie_id"),
      inverseJoinColumns = @JoinColumn(name = "actor_id"))
  private Set<Actor> actors;

  @ManyToMany(cascade = CascadeType.PERSIST)
  @JoinTable(
      name = "producer_movie",
      joinColumns = @JoinColumn(name = "movie_id"),
      inverseJoinColumns = @JoinColumn(name = "producer_id"))
  private Set<Producer> producers;

  @ManyToMany(cascade = CascadeType.PERSIST)
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
