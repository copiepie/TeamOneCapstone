package code.hub.ed.team1.repository;

import code.hub.ed.team1.model.BestActorNomination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface BestActorNominationRepository extends JpaRepository<BestActorNomination, Long> {
  @Query(
      "select ban from BestActorNomination ban where ban.nominationYear > :from and ban.nominationYear <= :to and ban.nominationResult = 'WON'")
  Set<BestActorNomination> findByYearRange(int from, int to);

  @Query(
      "select ban from BestActorNomination ban group by (actor.id) having count(ban.actor) >= :minimumTimesNominated AND ban.nominationResult = 'NOMINATED'")
  Set<BestActorNomination> findByMinTimesNominated(int minimumTimesNominated);

  @Query(
      """
          select ban from BestActorNomination ban
          group by (actor.id)
          having count(ban.actor) >= :minimumTimesNominated
          AND NOT EXISTS (
            select ban2 from BestActorNomination ban2
            where ban2.nominationResult = 'WON'
            and ban2.actor = ban.actor
          )
          """)
  Set<BestActorNomination> findByMinTimesNominatedAndNeverWon(int minimumTimesNominated);
}
