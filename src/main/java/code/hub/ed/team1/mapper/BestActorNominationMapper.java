package code.hub.ed.team1.mapper;

import code.hub.ed.team1.dto.BestActorNominationDto;
import code.hub.ed.team1.model.BestActorNomination;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BestActorNominationMapper {
  BestActorNominationMapper INSTANCE = Mappers.getMapper(BestActorNominationMapper.class);

  @Mappings({
    @Mapping(source = "actor.name", target = "actor"),
    @Mapping(source = "actor.id", target = "actorId"),
    @Mapping(source = "movie.title", target = "movie"),
    @Mapping(source = "movie.id", target = "movieId")
  })
  BestActorNominationDto toDto(BestActorNomination bestActorNomination);

  @Mappings({
    @Mapping(source = "actorId", target = "actor.id"),
    @Mapping(source = "movieId", target = "movie.id")
  })
  BestActorNomination toEntity(BestActorNominationDto bestActorNominationDto);
}
