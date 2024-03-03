package code.hub.ed.team1.bootstrap;

import code.hub.ed.team1.dto.*;
import code.hub.ed.team1.model.Genre;
import code.hub.ed.team1.model.Profession;
import code.hub.ed.team1.model.SalaryType;
import code.hub.ed.team1.service.api.MovieService;
import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.IntStream;

@Component
@AllArgsConstructor
public class SampleContent implements CommandLineRunner {

  private final MovieService movieService;

  @Override
  public void run(String... args) throws Exception {
    Lorem lorem = LoremIpsum.getInstance();

    IntStream.range(0, 10)
        .forEach(
            index -> {
              MovieDto movieDto = new MovieDto();
              movieDto.setTitle(lorem.getTitle(10));
              movieDto.setGenre(Genre.COMEDY);
              movieDto.setProductionBudget(BigDecimal.valueOf(1_000_000));
              movieDto.setYearOfRelease(1980);

              DirectorDto director = new DirectorDto();
              director.setName(lorem.getName());
              director.setSalaryType(SalaryType.FULL_PROJECT);
              director.setSalary(BigDecimal.valueOf(10_000));
              director.setProfession(Profession.DIRECTOR);

              movieDto.setDirector(director);

              ActorDto actor = new ActorDto();
              actor.setName(lorem.getName());
              actor.setSalaryType(SalaryType.FULL_PROJECT);
              actor.setSalary(BigDecimal.valueOf(10_000));
              actor.setProfession(Profession.ACTOR);

              movieDto.setActors(Set.of(actor));

              ProducerDto producer = new ProducerDto();
              producer.setName(lorem.getName());
              producer.setSalaryType(SalaryType.FULL_PROJECT);
              producer.setSalary(BigDecimal.valueOf(10_000));
              producer.setProfession(Profession.PRODUCER);

              movieDto.setProducers(Set.of(producer));

              CrewMemberDto crewMember = new CrewMemberDto();
              crewMember.setName(lorem.getName());
              crewMember.setSalaryType(SalaryType.FULL_PROJECT);
              crewMember.setSalary(BigDecimal.valueOf(10_000));
              crewMember.setProfession(Profession.CREW_MEMBER);

              movieDto.setCrewMembers(Set.of(crewMember));

              movieService.create(movieDto);

            });
  }
}
