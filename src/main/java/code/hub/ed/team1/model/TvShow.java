package code.hub.ed.team1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TvShow extends BaseEntity  {
  @ManyToOne
  @JoinColumn(name = "director_id", nullable = false)
  private Director director;

  @OneToMany(mappedBy = "tvShow")
  private List<Actor> actors;

  @OneToMany(mappedBy = "tvShow")
  private List<Producer> producers;

  @OneToMany(mappedBy = "tvShow")
  private List<CrewMember> crewMembers;

  @Column(nullable = false)
  private BigDecimal minBugdet;

  @Column(nullable = false)
  private BigDecimal maxBugdet;

  @Column(nullable = false)
  private LocalDate startingYear;

  private LocalDate endingYear;

  @Column(nullable = false)
  private Integer numberOfEpisodes;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String genre;
}
