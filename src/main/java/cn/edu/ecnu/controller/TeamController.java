package cn.edu.ecnu.controller;

import cn.edu.ecnu.domain.Team;
import cn.edu.ecnu.service.TeamService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("组队信息控制器")
@RestController
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @ApiOperation("通过tid查询队伍")
    @ResponseBody
    @GetMapping("/{tid}")
    public JSONObject findTeamById(@PathVariable String tid) {
        JSONObject object = new JSONObject();
        Team team = teamService.findTeamById(tid);
        object.put("team", team);
        return object;
    }
}
