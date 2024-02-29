package code.hub.ed.team1.service;

import code.hub.ed.team1.dto.TestDto;
import code.hub.ed.team1.mapper.TestMapper;
import code.hub.ed.team1.model.Test;
import code.hub.ed.team1.repository.TestRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TestServiceImpl implements TestService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestServiceImpl.class.getSimpleName());

    private final TestRepository testRepository;

    private final TestMapper testMapper;

    @Override
    public TestDto createOrUpdateTest(TestDto testDto) {
        LOGGER.debug("createOrUpdateTest");
        Test test = testMapper.testDtoToTest(testDto);
        test = testRepository.save(test);
        return testMapper.testToTestDto(test);
    }

    @Override
    public List<TestDto> findTests() {
        LOGGER.debug("findTests");
        return testMapper.testToTestDtoList(testRepository.findAll());
    }

    @Override
    public void deleteTest(long id) {
        LOGGER.debug("deleteTest");
        testRepository.deleteAllById(List.of(id));
    }
}
