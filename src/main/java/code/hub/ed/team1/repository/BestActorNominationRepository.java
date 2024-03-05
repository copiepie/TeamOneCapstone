package code.hub.ed.team1.repository;

import code.hub.ed.team1.model.BestActorNomination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface BestActorNominationRepository extends JpaRepository<BestActorNomination, Long> {
    @Query(
            "select ban from BestActorNomination ban where ban.nominationYear > :from and ban.nominationYear <= :to")
    Set<BestActorNomination> findByYearRange(int from, int to);
}
