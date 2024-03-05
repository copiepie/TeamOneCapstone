package code.hub.ed.team1.service;

import code.hub.ed.team1.exception.TvShowNotFoundException;
import code.hub.ed.team1.mapper.TvShowMapper;
import code.hub.ed.team1.model.*;
import code.hub.ed.team1.repository.TvShowRepository;
import code.hub.ed.team1.service.impl.TvShowServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

public class TvShowServiceTest {

  @Test
  public void test_calculateCost_tvShowNotMovie_exceptionIsThrown() {
    TvShowRepository tvShowRepository = Mockito.mock(TvShowRepository.class);
    Mockito.when(tvShowRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

    TvShowServiceImpl tvShowService = new TvShowServiceImpl(tvShowRepository, TvShowMapper.INSTANCE);
    Assertions.assertThatThrownBy(() -> tvShowService.calculateCost(1l))
        .isInstanceOf(TvShowNotFoundException.class)
        .hasMessage("No TV-Show with id '1' could be found");
  }

  @Test
  public void test_calculateCost_tvShowFound_salariesPerEpisode_allSalariesCalculatedAsExpected() {
    Director matt =
        Director.builder()
            .salary(BigDecimal.valueOf(25_060.09))
            .salaryType(SalaryType.SINGLE_EPISODE)
            .build();

    Actor homer =
        Actor.builder()
            .id(new Random().nextLong())
            .salary(BigDecimal.valueOf(12_099.01))
            .salaryType(SalaryType.SINGLE_EPISODE)
            .build();

    Actor marge =
        Actor.builder()
            .id(new Random().nextLong())
            .salary(BigDecimal.valueOf(17_921.22))
            .salaryType(SalaryType.SINGLE_EPISODE)
            .build();

    Producer rupert =
        Producer.builder()
            .id(new Random().nextLong())
            .salary(BigDecimal.valueOf(5_999.78))
            .salaryType(SalaryType.SINGLE_EPISODE)
            .build();

    Producer yeardly =
        Producer.builder()
            .id(new Random().nextLong())
            .salary(BigDecimal.valueOf(6_555.92))
            .salaryType(SalaryType.SINGLE_EPISODE)
            .build();

    CrewMember hank =
        CrewMember.builder()
            .id(new Random().nextLong())
            .salary(BigDecimal.valueOf(998.08))
            .salaryType(SalaryType.SINGLE_EPISODE)
            .build();

    CrewMember lisa =
        CrewMember.builder()
            .id(new Random().nextLong())
            .salary(BigDecimal.valueOf(1_542.11))
            .salaryType(SalaryType.SINGLE_EPISODE)
            .build();

    TvShow tvShow =
        TvShow.builder()
            .numberOfEpisodes(199)
            .actors(Set.of(homer, marge))
            .director(matt)
            .producers(Set.of(rupert, yeardly))
            .crewMembers(Set.of(lisa, hank))
            .build();

    TvShowRepository tvShowRepository = Mockito.mock(TvShowRepository.class);
    Mockito.when(tvShowRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(tvShow));

    TvShowServiceImpl tvShowService = new TvShowServiceImpl(tvShowRepository, TvShowMapper.INSTANCE);
    BigDecimal totalCost = tvShowService.calculateCost(1l);
    Assertions.assertThat(totalCost)
        .usingComparator(BigDecimal::compareTo)
        .isEqualTo(BigDecimal.valueOf(13_965_065.79));
  }
}
