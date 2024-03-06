package code.hub.ed.team1.controller;

import code.hub.ed.team1.dto.ProducerDto;
import code.hub.ed.team1.service.api.ProducerService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/producer")
public class ProducerController {

    private final ProducerService producerService;

    @GetMapping("/{id}")
    public ProducerDto getProducer(@PathVariable("id") long id) {
        return producerService.read(id);
    }

    @PostMapping
    public ProducerDto createProducer(ProducerDto producerDto) {
        return producerService.create(producerDto);
    }

    @PutMapping
    public ProducerDto updateProducer(ProducerDto producerDto) {
        return producerService.update(producerDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProducer(@PathVariable("id") long id) {
        producerService.delete(id);
    }
}
