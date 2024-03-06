package code.hub.ed.team1.service.impl;

import code.hub.ed.team1.dto.CrewMemberDto;
import code.hub.ed.team1.mapper.CrewMemberMapper;
import code.hub.ed.team1.model.CrewMember;
import code.hub.ed.team1.repository.CrewMemberRepository;
import code.hub.ed.team1.service.api.CrewMemberService;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CrewMemberServiceImpl implements CrewMemberService {

  private CrewMemberRepository crewMemberRepository;

  private CrewMemberMapper crewMemberMapper;


  @Override
  public CrewMemberDto create(CrewMemberDto crewMemberDto) {
    CrewMember crewMember = crewMemberMapper.crewMemberDtoToCrewMember(crewMemberDto);
    crewMemberRepository.save(crewMember);
    return crewMemberMapper.crewMemberToCrewMemberDto(crewMember);
  }

  @Override
  public CrewMemberDto read(Long id) {
    Optional<CrewMember> optionalCrewMember = crewMemberRepository.findById(id);
    CrewMember crewMember = optionalCrewMember.orElseThrow(IllegalArgumentException::new);
    return crewMemberMapper.crewMemberToCrewMemberDto(crewMember);
  }

  @Override
  public CrewMemberDto update(CrewMemberDto crewMemberDto) {
    Optional<CrewMember> optionalCrewMember = crewMemberRepository.findById(crewMemberDto.getId());
    if ( optionalCrewMember.isPresent()){
      CrewMember crewMember = crewMemberMapper.crewMemberDtoToCrewMember(crewMemberDto);
      crewMemberRepository.save(crewMember);
      return crewMemberMapper.crewMemberToCrewMemberDto(crewMember);
    } else {
      // TODO Change to somehting like MovieNotFoundException
      throw new IllegalArgumentException("CrewMember not found");

    }
  }

  @Override
  public void delete(Long id) {
    crewMemberRepository.deleteById(id);
  }
}
