package code.hub.ed.team1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Entity
@Builder
@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final long id;

    private String description;

    private LocalDate creationDate;
}
