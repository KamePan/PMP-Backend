package cn.edu.ecnu.service;

import cn.edu.ecnu.domain.Team;
import cn.edu.ecnu.domain.TeamStudent;

import java.util.List;

public interface ITeamService {

    Team findTeamById(String tid);

    Team insertTeam(Team team, String uid);

    List<Team> findTeamsBySid(String sid);

    void deleteStudentFromTeam(TeamStudent teamStudent);

    void inviteMember(TeamStudent teamStudent);

    Team updateTeamName(String teamid, String teamname);
}
