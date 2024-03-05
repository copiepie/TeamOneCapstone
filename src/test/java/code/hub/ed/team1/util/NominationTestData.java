package code.hub.ed.team1.util;

import code.hub.ed.team1.model.*;
import code.hub.ed.team1.repository.ActorRepository;
import code.hub.ed.team1.repository.BestActorNominationRepository;
import code.hub.ed.team1.repository.DirectoryRepository;
import code.hub.ed.team1.repository.MovieRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@AllArgsConstructor
public class NominationTestData {

  private Movie movie;
  private Actor actor;
  private BestActorNomination bestActorNomination;

  public static class Builder {
    private final ActorRepository actorRepository;
    private final MovieRepository movieRepository;
    private final BestActorNominationRepository bestActorNominationRepository;
    private final DirectoryRepository directoryRepository;
    private Movie.MovieBuilder movieBuilder;
    private Actor.ActorBuilder currentActorBuilder;
    private BestActorNomination.BestActorNominationBuilder currentNominationBuilder;
    private Movie movie;
    private Actor actor;
    private BestActorNomination bestActorNomination;

    public Builder(
        ActorRepository actorRepository,
        MovieRepository movieRepository,
        BestActorNominationRepository bestActorNominationRepository,
        DirectoryRepository directoryRepository) {
      this.actorRepository = actorRepository;
      this.movieRepository = movieRepository;
      this.bestActorNominationRepository = bestActorNominationRepository;
      this.directoryRepository = directoryRepository;
    }

    public Builder makeMovie() {
      this.movieBuilder = Movie.builder();
      return this;
    }

    public Builder withTitle(String title) {
      this.movieBuilder.title(title);
      return this;
    }

    public Builder withGenre(Genre genre) {
      this.movieBuilder.genre(genre);
      return this;
    }

    public Builder withDirector() {
      Director director =
          Director.builder()
              .salary(BigDecimal.ONE)
              .salaryType(SalaryType.FULL_PROJECT)
              .name("Oliver Stone")
              .build();
      this.movieBuilder.director(director);
      return this;
    }

    public Builder withProductionBudget(BigDecimal productionBudget) {
      this.movieBuilder.productionBudget(productionBudget);
      return this;
    }

    public Builder withYearOfRelease(int yearOfRelease) {
      this.movieBuilder.yearOfRelease(yearOfRelease);
      return this;
    }

    public Builder andActor() {
      this.currentActorBuilder = Actor.builder();
      return this;
    }

    public Builder withName(String name) {
      this.currentActorBuilder.name(name);
      return this;
    }

    public Builder withSalary(BigDecimal salary) {
      this.currentActorBuilder.salary(salary);
      return this;
    }

    public Builder withSalaryType(SalaryType salaryType) {
      this.currentActorBuilder.salaryType(salaryType);
      return this;
    }

    public Builder buildMovieAndActor() {
      this.movieBuilder.actors(Set.of(currentActorBuilder.build()));
      this.movie = this.movieRepository.save(this.movieBuilder.build());
      this.actor = movie.getActors().iterator().next();
      return this;
    }

    public Builder andNomination() {
      this.currentNominationBuilder = BestActorNomination.builder();
      return this;
    }

    public Builder addNominee() {
      this.currentNominationBuilder.actor(this.actor);
      return this;
    }

    public Builder inGenre(Genre genre) {
      this.currentNominationBuilder.genre(genre);
      return this;
    }

    public Builder withNominationYear(int nominationYear) {
      this.currentNominationBuilder.nominationYear(nominationYear);
      return this;
    }

    public Builder withNominationResult(NominationResult nominationResult) {
      this.currentNominationBuilder.nominationResult(nominationResult);
      return this;
    }

    public Builder withCategory(Category category) {
      this.currentNominationBuilder.category(category);
      return this;
    }

    public Builder withMovie() {
      this.currentNominationBuilder.movie(movie);
      return this;
    }

    public NominationTestData build() {
      bestActorNomination = bestActorNominationRepository.save(currentNominationBuilder.build());
      return new NominationTestData(movie, actor, bestActorNomination);
    }
  }
}
