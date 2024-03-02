package code.hub.ed.team1.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@DiscriminatorValue(value = Profession.Val._DIRECTOR)
public class Director extends People {}
