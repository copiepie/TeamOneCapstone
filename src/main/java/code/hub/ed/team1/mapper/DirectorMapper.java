package code.hub.ed.team1.mapper;

import code.hub.ed.team1.dto.DirectorDto;
import code.hub.ed.team1.model.Director;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DirectorMapper {


  Director directorDtoToDirector(DirectorDto directorDto);

  DirectorDto directorDto(Director director);
}
