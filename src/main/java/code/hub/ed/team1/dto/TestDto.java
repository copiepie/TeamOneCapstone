package code.hub.ed.team1.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TestDto {
    private long id;

    private String description;

    private LocalDate creationDate;
}
