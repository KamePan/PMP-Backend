package cn.edu.ecnu.service;

import cn.edu.ecnu.dao.TeamMapper;
import cn.edu.ecnu.domain.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    @Autowired
    private TeamMapper teamMapper;

    public Team findTeamById(String tid) {
        Team team = teamMapper.selectByPrimaryKey(tid);
        return team;
    }
}
