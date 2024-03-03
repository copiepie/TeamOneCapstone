package code.hub.ed.team1.service.impl;

import code.hub.ed.team1.dto.TvShowDto;
import code.hub.ed.team1.exception.TvShowNotFoundException;
import code.hub.ed.team1.model.People;
import code.hub.ed.team1.model.TvShow;
import code.hub.ed.team1.repository.TvShowRepository;
import code.hub.ed.team1.service.api.Calculatable;
import code.hub.ed.team1.service.api.TvShowService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class TvShowServiceImpl implements TvShowService, Calculatable {

  private final TvShowRepository tvShowRepository;

  @Override
  public TvShowDto create(TvShowDto tvShowDto) {
    return null;
  }

  @Override
  public TvShowDto read(Long id) {
    return null;
  }

  @Override
  public TvShowDto update(TvShowDto tvShowDto) {
    return null;
  }

  @Override
  public void delete(Long id) {}

  @Override
  public BigDecimal calculateCost(long id) {
    Optional<TvShow> optionalTvShow = tvShowRepository.findById(id);
    TvShow tvShow =
        optionalTvShow.orElseThrow(
            () ->
                new TvShowNotFoundException(
                    String.format("No TV-Show with id '%d' could be found", id)));
    BigDecimal directorSalary =
        tvShow.getDirector().getSalary().multiply(BigDecimal.valueOf(tvShow.getNumberOfEpisodes()));
    BinaryOperator<BigDecimal> accumulator = (sub, sal) -> sub.add(sal);
    BigDecimal actorsTotalSalary =
        tvShow.getActors().stream()
            .map(actor -> calculateTotalSalary(actor, tvShow.getNumberOfEpisodes()))
            .reduce(BigDecimal.ZERO, accumulator);
    BigDecimal producersTotalSalary =
        tvShow.getProducers().stream()
            .map(producer -> calculateTotalSalary(producer, tvShow.getNumberOfEpisodes()))
            .reduce(BigDecimal.ZERO, accumulator);
    BigDecimal crewMembersTotalSalary =
        tvShow.getCrewMembers().stream()
            .map(crewMember -> calculateTotalSalary(crewMember, tvShow.getNumberOfEpisodes()))
            .reduce(BigDecimal.ZERO, accumulator);

    return Stream.of(
            directorSalary, actorsTotalSalary, producersTotalSalary, crewMembersTotalSalary)
        .reduce(BigDecimal.ZERO, accumulator);
  }

  private BigDecimal calculateTotalSalary(People person, Integer numberOfEpisodes) {
    return person.getSalary().multiply(BigDecimal.valueOf(numberOfEpisodes));
  }
}
