package code.hub.ed.team1.service.impl;

import code.hub.ed.team1.dto.DirectorDto;
import code.hub.ed.team1.mapper.DirectorMapper;
import code.hub.ed.team1.model.Director;
import code.hub.ed.team1.repository.DirectoryRepository;
import code.hub.ed.team1.service.api.DirectorService;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DirectorServiceImpl implements DirectorService {


  private DirectoryRepository directoryRepository;

  private DirectorMapper directorMapper;

  @Override
  public DirectorDto create(DirectorDto directorDto) {
    Director director = directorMapper.directorDtoToDirector(directorDto);
    directoryRepository.save(director);
    return directorMapper.directorDto(director);
  }

  @Override
  public DirectorDto read(Long id) {
    Optional<Director> optionalDirector = directoryRepository.findById(id);
    Director director = optionalDirector.orElseThrow(IllegalArgumentException::new);
    return directorMapper.directorDto(director);
  }

  @Override
  public DirectorDto update(DirectorDto directorDto) {
    Optional<Director> optionalDirector = directoryRepository.findById(directorDto.getId());
    if (optionalDirector.isPresent()) {
      Director director = directorMapper.directorDtoToDirector(directorDto);
      directoryRepository.save(director);
      return directorMapper.directorDto(director);
    } else {
      throw new IllegalArgumentException("Director not found");
    }
  }

  @Override
  public void delete(Long id) {
    directoryRepository.deleteById(id);
  }
}
