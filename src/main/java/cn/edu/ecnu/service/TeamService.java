package cn.edu.ecnu.service;

import cn.edu.ecnu.dao.TeamMapper;
import cn.edu.ecnu.dao.TeamStudentMapper;
import cn.edu.ecnu.domain.Team;
import cn.edu.ecnu.domain.TeamStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    @Autowired
    private TeamMapper teamMapper;

    @Autowired
    private TeamStudentMapper teamStudentMapper;

    public Team findTeamById(String tid) {
        Team team = teamMapper.selectByPrimaryKey(tid);
        return team;
    }

    public void insertTeam(Team team, String uid) {
        TeamStudent teamStudent = new TeamStudent(team.getTeamid(), uid);
        teamStudentMapper.insert(teamStudent);
        teamMapper.insert(team);
    }
}
