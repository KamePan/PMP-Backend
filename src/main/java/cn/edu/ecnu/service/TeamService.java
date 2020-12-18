package cn.edu.ecnu.service;

import cn.edu.ecnu.dao.TeamMapper;
import cn.edu.ecnu.dao.TeamStudentMapper;
import cn.edu.ecnu.domain.Team;
import cn.edu.ecnu.domain.TeamStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

// @CacheConfig：该注解是用来开启声明的类参与缓存,如果方法内的@Cacheable注解没有添加key值，
//              那么会自动使用cahceNames配置参数并且追加方法名。
// @Cacheable：配置方法的缓存参数，可自定义缓存的key以及value。

@CacheConfig(cacheNames = "Team", keyGenerator = "keyGenerator")
@Service
public class TeamService {

    @Autowired
    private TeamMapper teamMapper;

    @Autowired
    private TeamStudentMapper teamStudentMapper;

    /*@Autowired
    private RedisTemplate<String, Object> redisTemplate;*/

    @Cacheable
    public Team findTeamById(String tid) {
        Team team = null;

        /*if (redisTemplate.hasKey(tid)) {
            team = (Team) redisTemplate.opsForValue().get(tid);
        } else {
        team = teamMapper.selectByPrimaryKey(tid);
        redisTemplate.opsForValue().set(tid, team, 1, TimeUnit.MINUTES); //设置 1 分钟过期时间
        }*/

        team = teamMapper.selectByPrimaryKey(tid);
        return team;
    }

    public void insertTeam(Team team, String uid) {
        TeamStudent teamStudent = new TeamStudent(team.getTeamid(), uid);
        teamStudentMapper.insert(teamStudent);
        teamMapper.insert(team);
    }

    public void inviteMember(TeamStudent teamStudent) {
        teamStudentMapper.insert(teamStudent);
    }
}
