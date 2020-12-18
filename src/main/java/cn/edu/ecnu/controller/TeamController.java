package cn.edu.ecnu.controller;

import cn.edu.ecnu.domain.Team;
import cn.edu.ecnu.domain.TeamStudent;
import cn.edu.ecnu.service.TeamService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Api(tags = "组队信息控制器")
@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @ApiOperation("通过teamid查询队伍")
    @ResponseBody
    @GetMapping("/{teamid}")
    public JSONObject findTeamById(@PathVariable String teamid) {
        JSONObject object = new JSONObject();
        Team team = teamService.findTeamById(teamid);
        object.put("team", team);
        return object;
    }

    @ApiOperation("创建队伍")
    @ResponseBody
    @PostMapping("/{uid}")
    public JSONObject createTeam(@RequestBody Team team, @PathVariable String uid) {
        JSONObject object = new JSONObject();
        String teamid = "T" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        team.setTeamid(teamid);
        teamService.insertTeam(team, uid);
        object.put("team", team);
        return object;
    }

    @ApiOperation("根据 username 邀请成员加入项目")
    @PostMapping("/invite")
    @ResponseBody
    public JSONObject InviteMember(@RequestBody TeamStudent teamStudent) {
        JSONObject object = new JSONObject();
        teamService.inviteMember(teamStudent);
        object.put("invite", teamStudent);
        return object;
    }
}
