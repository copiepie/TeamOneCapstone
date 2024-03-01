package code.hub.ed.team1.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue(value = Profession.Val._CREW_MEMBER)
public class CrewMember extends People {
}
