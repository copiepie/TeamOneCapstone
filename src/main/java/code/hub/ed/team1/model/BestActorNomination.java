package code.hub.ed.team1.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BestActorNomination extends BaseEntity {

    @Column(nullable = false)
    private Integer nominationYear;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "actor_id", nullable = false)
    private Actor actor;
}
