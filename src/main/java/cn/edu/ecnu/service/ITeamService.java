package cn.edu.ecnu.service;

import cn.edu.ecnu.domain.Team;
import cn.edu.ecnu.domain.TeamStudent;

public interface ITeamService {

    public Team findTeamById(String tid);

    public void insertTeam(Team team, String uid);

    public void inviteMember(TeamStudent teamStudent);

}
