package code.hub.ed.team1.repository;

import code.hub.ed.team1.model.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Long> {}
