package code.hub.ed.team1.service;

import code.hub.ed.team1.dto.TestDto;

import java.util.List;

public interface TestService {
    TestDto createOrUpdateTest(TestDto testDto);

    List<TestDto> findTests();

    void deleteTest(long id);
}
