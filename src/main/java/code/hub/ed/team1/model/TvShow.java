package code.hub.ed.team1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "TV_SHOW")
public class TvShow extends BaseEntity  {

  private BigDecimal minBugdet;

  private BigDecimal maxBugdet;

  private Date startingYear;
  private Date endingYear;

  private int numberOfEpisodes;

  private String title;

  @ManyToOne
  @JoinColumn(name = "DIRECTOR")
  private Director director;

  private List<Actor> actors;

  private List<Producer> producers;

  private String genre;

  public Director getDirector() {
    return director;
  }

  public void setDirector(Director director) {
    this.director = director;
  }

}
