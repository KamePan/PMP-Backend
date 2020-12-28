package cn.edu.ecnu.controller;

import cn.edu.ecnu.domain.Student;
import cn.edu.ecnu.domain.Team;
import cn.edu.ecnu.domain.TeamStudent;
import cn.edu.ecnu.service.IStudentService;
import cn.edu.ecnu.service.ITeamService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Api(tags = "组队信息控制器")
@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private ITeamService teamService;

    @Autowired
    private IStudentService studentService;

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
    @PostMapping("/create/{uid}")
    public JSONObject createTeam(@RequestBody Team team, @PathVariable String uid) {
        String teamid = "T" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        team.setTeamid(teamid);
        Team savedTeam = teamService.insertTeam(team, uid);
        System.out.println(savedTeam);
        return (JSONObject) JSON.toJSON(savedTeam);
    }

    @ApiOperation("根据 username 邀请成员加入项目")
    @PostMapping("/invite/{teamid}")
    public JSONObject InviteMember(@PathVariable String teamid, @RequestBody Map<String, String> paramsMap) {
        Student student = studentService.findUserByUsername(paramsMap.get("username"));
        TeamStudent teamStudent = new TeamStudent().setSid(student.getSid()).setTeamid(teamid);
        teamService.inviteMember(teamStudent);
        return (JSONObject) JSON.toJSON(student);
    }

    @ApiOperation("更改组队名字")
    @PutMapping("/{teamid}")
    public JSONObject modifyTeamName(@PathVariable String teamid, @RequestBody Map<String, String> paramsMap) {
        Team team = teamService.updateTeamName(teamid, paramsMap.get("newName"));
        return (JSONObject) JSON.toJSON(team);
    }


    @ApiOperation("根据 sid 获取参与的小组")
    @GetMapping("/stu/{sid}")
    public JSONObject findTeamsBySid(@PathVariable String sid) {
        JSONObject object = new JSONObject();
        List<Team> teams = teamService.findTeamsBySid(sid);
        object.put("teams", teams);
        return object;
    }

    @ApiOperation("退出组队")
    @PostMapping
    public JSONObject quitTeam(@RequestBody TeamStudent teamStudent) {
        teamService.deleteStudentFromTeam(teamStudent);
        return (JSONObject) JSON.toJSON(teamStudent);
    }

}
