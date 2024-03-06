package code.hub.ed.team1.mapper;

import code.hub.ed.team1.dto.ProducerDto;
import code.hub.ed.team1.model.Producer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProducerMapper {


  Producer producerDtoToProducer(ProducerDto producerDto);

  ProducerDto producerToProducerDto(Producer producer);

}
