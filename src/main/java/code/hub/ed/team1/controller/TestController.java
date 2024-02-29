package code.hub.ed.team1.controller;

import code.hub.ed.team1.dto.TestDto;
import code.hub.ed.team1.service.TestService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/test")
public class TestController {
    private final TestService testService;

    @PostMapping
    public TestDto createTest(@RequestBody TestDto testDto) {
        return testService.createOrUpdateTest(testDto);
    }

    @PutMapping
    public TestDto updateTest(@RequestBody TestDto testDto) {
        return testService.createOrUpdateTest(testDto);
    }

    @DeleteMapping("/{id}")
    public void deleteTest(@PathVariable long id) {
        testService.deleteTest(id);
    }

    @GetMapping
    public List<TestDto> readTests() {
        return testService.findTests();
    }
}
