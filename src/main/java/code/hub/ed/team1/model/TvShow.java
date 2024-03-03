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
public class TvShow extends BaseEntity {
  @ManyToOne
  @JoinColumn(name = "director_id", nullable = false)
  private Director director;

  @ManyToMany
  @JoinTable(
      name = "actor_tvshow",
      joinColumns = @JoinColumn(name = "actor_id"),
      inverseJoinColumns = @JoinColumn(name = "tvshow_id"))
  private Set<Actor> actors;

  @ManyToMany
  @JoinTable(
      name = "producer_tvshow",
      joinColumns = @JoinColumn(name = "producer_id"),
      inverseJoinColumns = @JoinColumn(name = "tvshow_id"))
  private Set<Producer> producers;

  @ManyToMany
  @JoinTable(
      name = "crew_tvshow",
      joinColumns = @JoinColumn(name = "crew_id"),
      inverseJoinColumns = @JoinColumn(name = "tvshow_id"))
  private Set<CrewMember> crewMembers;

  @Column(nullable = false)
  private BigDecimal minBugdet;

  @Column(nullable = false)
  private BigDecimal maxBugdet;

  @Column(nullable = false)
  private Integer startingYear;

  private Integer endingYear;

  @Column(nullable = false)
  private Integer numberOfEpisodes;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Genre genre;
}
