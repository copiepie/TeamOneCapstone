package code.hub.ed.team1.mapper;

import code.hub.ed.team1.dto.TestDto;
import code.hub.ed.team1.model.Test;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TestMapper {

    TestDto testToTestDto(Test test);

    Test testDtoToTest(TestDto testDto);

    List<TestDto> testToTestDtoList(List<Test> tests);
}
