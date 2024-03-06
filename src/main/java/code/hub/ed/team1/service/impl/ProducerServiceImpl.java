package code.hub.ed.team1.service.impl;

import code.hub.ed.team1.dto.ProducerDto;
import code.hub.ed.team1.mapper.ProducerMapper;
import code.hub.ed.team1.model.Producer;
import code.hub.ed.team1.repository.ProducerRepository;
import code.hub.ed.team1.service.api.ProducerService;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProducerServiceImpl implements ProducerService {

  private ProducerRepository producerRepository;

  private ProducerMapper producerMapper;

  @Override
  public ProducerDto create(ProducerDto producerDto) {
    Producer producer = producerMapper.producerDtoToProducer(producerDto);
    producerRepository.save(producer);
    return producerMapper.producerToProducerDto(producer);
  }

  @Override
  public ProducerDto read(Long id) {
    Optional<Producer> optionalProducer = producerRepository.findById(id);
    Producer producer = optionalProducer.orElseThrow(IllegalArgumentException::new);
    return producerMapper.producerToProducerDto(producer);
  }

  @Override
  public ProducerDto update(ProducerDto producerDto) {
    Optional<Producer> optionalProducer = producerRepository.findById(producerDto.getId());
    if (optionalProducer.isPresent()) {
      Producer producer = producerMapper.producerDtoToProducer(producerDto);
      producerRepository.save(producer);
      return producerMapper.producerToProducerDto(producer);
    } else {
      throw new IllegalArgumentException("Producer not found");
    }
  }

  @Override
  public void delete(Long id) {
    producerRepository.deleteById(id);
  }
}
