package code.hub.ed.team1.repository;

import code.hub.ed.team1.model.CrewMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrewMemberRepository extends JpaRepository<CrewMember, Long> {
}
