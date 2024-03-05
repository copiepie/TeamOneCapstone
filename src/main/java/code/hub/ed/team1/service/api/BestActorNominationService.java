package code.hub.ed.team1.service.api;

import code.hub.ed.team1.dto.BestActorNominationDto;

import java.util.Map;
import java.util.Set;

public interface BestActorNominationService extends GeneralService<BestActorNominationDto, Long> {
  Map<Integer, Set<BestActorNominationDto>> getBestActorNominationsForYearsRange(int from, int to);

  Set<BestActorNominationDto> getByMinTimesNominated(int minimumTimesNominated);
}
