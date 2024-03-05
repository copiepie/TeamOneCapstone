package code.hub.ed.team1.service.impl;

import code.hub.ed.team1.dto.BestActorNominationDto;
import code.hub.ed.team1.mapper.BestActorNominationMapper;
import code.hub.ed.team1.model.BestActorNomination;
import code.hub.ed.team1.repository.BestActorNominationRepository;
import code.hub.ed.team1.service.api.BestActorNominationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BestActorNominationServiceImpl implements BestActorNominationService {

  private static final String BEST_ACTOR_NOM_NOT_FOUND_MSG =
      "No Nomination with id '%d' could be found";

  private final BestActorNominationMapper bestActorNominationMapper;

  private final BestActorNominationRepository bestActorNominationRepository;

  @Override
  public BestActorNominationDto create(BestActorNominationDto bestActorNominationDto) {
    BestActorNomination bestActorNomination =
        bestActorNominationMapper.toEntity(bestActorNominationDto);
    return bestActorNominationMapper.toDto(bestActorNominationRepository.save(bestActorNomination));
  }

  @Override
  public BestActorNominationDto read(Long id) {
    Optional<BestActorNomination> optionalBestActorNomination =
        bestActorNominationRepository.findById(id);
    BestActorNomination bestActorNomination =
        optionalBestActorNomination.orElseThrow(
            () -> new EntityNotFoundException(String.format(BEST_ACTOR_NOM_NOT_FOUND_MSG, id)));
    return bestActorNominationMapper.toDto(bestActorNomination);
  }

  @Override
  public BestActorNominationDto update(BestActorNominationDto bestActorNominationDto) {
    Optional<BestActorNomination> optionalBestActorNomination =
        bestActorNominationRepository.findById(bestActorNominationDto.getId());
    optionalBestActorNomination.orElseThrow(
        () ->
            new EntityNotFoundException(
                String.format(BEST_ACTOR_NOM_NOT_FOUND_MSG, bestActorNominationDto.getId())));

    BestActorNomination bestActorNomination =
        bestActorNominationMapper.toEntity(bestActorNominationDto);
    return bestActorNominationMapper.toDto(bestActorNominationRepository.save(bestActorNomination));
  }

  @Override
  public void delete(Long id) {
    bestActorNominationRepository.deleteById(id);
  }

  @Override
  public Map<Integer, Set<BestActorNominationDto>> getBestActorNominationsForYearsRange(
      int from, int to) {
    Set<BestActorNomination> bestActorNominationsInTimespan =
        bestActorNominationRepository.findByYearRange(from, to);

    if (bestActorNominationsInTimespan.isEmpty()) {
      return Collections.emptyMap();
    }
    Set<Integer> years =
        bestActorNominationsInTimespan.stream()
            .map(BestActorNomination::getNominationYear)
            .collect(Collectors.toSet());

    Map<Integer, Set<BestActorNominationDto>> bestActorNominationsForYearsRange = new HashMap<>();
    for (Integer year : years) {
      Set<BestActorNominationDto> match =
          bestActorNominationsInTimespan.stream()
              .filter(bestActorNomination -> bestActorNomination.getNominationYear() == year)
              .map(bestActorNominationMapper::toDto)
              .collect(Collectors.toSet());
      bestActorNominationsForYearsRange.put(year, match);
    }

    return bestActorNominationsForYearsRange;
  }
}
