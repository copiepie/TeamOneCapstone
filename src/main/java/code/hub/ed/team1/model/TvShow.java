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
  
  private String title;
  
  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "director_id", nullable = false)
  private Director director;
  
  @ManyToMany(cascade = CascadeType.PERSIST)
  @JoinTable(
          name = "actor_tvshow",
          joinColumns = @JoinColumn(name = "tvshow_id"),
          inverseJoinColumns = @JoinColumn(name = "actor_id"))
  private Set<Actor> actors;
  
  @ManyToMany(cascade = CascadeType.PERSIST)
  @JoinTable(
          name = "producer_tvshow",
          joinColumns = @JoinColumn(name = "tvshow_id"),
          inverseJoinColumns = @JoinColumn(name = "producer_id"))
  private Set<Producer> producers;
  
  @ManyToMany(cascade = CascadeType.PERSIST)
  @JoinTable(
          name = "crew_tvshow",
          joinColumns = @JoinColumn(name = "tvshow_id"),
          inverseJoinColumns = @JoinColumn(name = "crew_member_id"))
  private Set<CrewMember> crewMembers;
  
  @Enumerated
  @Column(nullable = false)
  private Genre genre;
  
  @Column(nullable = false)
  private BigDecimal minBudgetPerEpisode;
  
  @Column(nullable = false)
  private BigDecimal maxBudgetPerEpisode;
  
  @Column(nullable = false)
  private Integer startingYear;
  
  @Column(nullable = false)
  private Integer endingYear;
  
  @Column(nullable = false)
  private Integer numberOfEpisodes;
}
