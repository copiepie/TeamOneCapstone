package code.hub.ed.team1.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Profession {
  ACTOR(Val._ACTOR),
  DIRECTOR(Val._DIRECTOR),
  PRODUCER(Val._PRODUCER),
  CREW_MEMBER(Val._CREW_MEMBER);

  final String name;

  public static class Val {
    public static final String _ACTOR = "actor";
    public static final String _DIRECTOR = "director";
    public static final String _PRODUCER = "producer";
    public static final String _CREW_MEMBER = "crew_member";
  }
}
