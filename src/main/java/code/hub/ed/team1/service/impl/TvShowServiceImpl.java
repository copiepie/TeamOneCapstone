package code.hub.ed.team1.service.impl;

import code.hub.ed.team1.dto.TvShowDto;
import code.hub.ed.team1.exception.TvShowNotFoundException;
import code.hub.ed.team1.mapper.TvShowMapper;
import code.hub.ed.team1.model.Genre;
import code.hub.ed.team1.model.People;
import code.hub.ed.team1.model.TvShow;
import code.hub.ed.team1.repository.TvShowRepository;
import code.hub.ed.team1.service.api.Calculatable;
import code.hub.ed.team1.service.api.TvShowService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class TvShowServiceImpl implements TvShowService {
	
	private final TvShowRepository tvShowRepository;
	private final TvShowMapper tvShowMapper;
	
	@Override
	public TvShowDto create(TvShowDto tvShowDto) {
		TvShow tvShow = tvShowMapper.tvShowDtoToTvShow(tvShowDto);
		TvShow savedTvShow = tvShowRepository.save(tvShow);
		return (TvShowDto) tvShowMapper.tvShowToTvShowDto(savedTvShow);
	}
	
	
	@Override
	public TvShowDto read(Long id) {
		TvShow tvShow = findTvShowById(id);
		return tvShowMapper.tvShowToTvShowDto(tvShow);
	}
	
	@Override
	public TvShowDto update(TvShowDto tvShowDto) {
		Optional<TvShow> optionalTvShow = tvShowRepository.findById(tvShowDto.getId());
		if (optionalTvShow.isPresent()) {
			TvShow tvShow = tvShowMapper.tvShowDtoToTvShow(tvShowDto);
			tvShow = tvShowRepository.save(tvShow);
			return tvShowMapper.tvShowToTvShowDto(tvShow);
		} else {
			throw new TvShowNotFoundException("TvShow with id " + tvShowDto.getId() + " not found");
		}
	}
	
	@Override
	public void delete(Long id) {
		tvShowRepository.deleteById(id);
	}
	
	@Override
	public List<TvShow> findTvShowsByGenre(Genre genre) {
		return tvShowRepository.findByGenre(genre);
	}
	
	@Override
	public List<TvShowDto> findTvShowsByYear(Integer year) {
		List<TvShow> tvShows = tvShowRepository.findByStartingYear(year);
		return tvShowMapper.tvShowToTvShowDtoList(tvShows);
	}
	
	@Override
	public List<TvShow> findTvShowsByTitle(String title) {
		return tvShowRepository.findByTitleContaining(title);
	}
	
	private TvShow findTvShowById(Long id) {
		Optional<TvShow> tvShowOptional = tvShowRepository.findById(id);
		if (tvShowOptional.isEmpty()) {
			throw new TvShowNotFoundException("TvShow with id " + id + " not found");
		}
		return tvShowOptional.get();
	}

  private final TvShowRepository tvShowRepository;

  @Override
  public TvShowDto create(TvShowDto tvShowDto) {
    return null;
  }

  @Override
  public TvShowDto read(Long id) {
    return null;
  }

  @Override
  public TvShowDto update(TvShowDto tvShowDto) {
    return null;
  }

  @Override
  public void delete(Long id) {}

  @Override
  public BigDecimal calculateCost(long id) {
    Optional<TvShow> optionalTvShow = tvShowRepository.findById(id);
    TvShow tvShow =
        optionalTvShow.orElseThrow(
            () ->
                new TvShowNotFoundException(
                    String.format("No TV-Show with id '%d' could be found", id)));
    BigDecimal directorSalary =
        tvShow.getDirector().getSalary().multiply(BigDecimal.valueOf(tvShow.getNumberOfEpisodes()));
    BinaryOperator<BigDecimal> accumulator = (sub, sal) -> sub.add(sal);
    BigDecimal actorsTotalSalary =
        tvShow.getActors().stream()
            .map(actor -> calculateTotalSalary(actor, tvShow.getNumberOfEpisodes()))
            .reduce(BigDecimal.ZERO, accumulator);
    BigDecimal producersTotalSalary =
        tvShow.getProducers().stream()
            .map(producer -> calculateTotalSalary(producer, tvShow.getNumberOfEpisodes()))
            .reduce(BigDecimal.ZERO, accumulator);
    BigDecimal crewMembersTotalSalary =
        tvShow.getCrewMembers().stream()
            .map(crewMember -> calculateTotalSalary(crewMember, tvShow.getNumberOfEpisodes()))
            .reduce(BigDecimal.ZERO, accumulator);

    return Stream.of(
            directorSalary, actorsTotalSalary, producersTotalSalary, crewMembersTotalSalary)
        .reduce(BigDecimal.ZERO, accumulator);
  }

  private BigDecimal calculateTotalSalary(People person, Integer numberOfEpisodes) {
    return person.getSalary().multiply(BigDecimal.valueOf(numberOfEpisodes));
  }
}
