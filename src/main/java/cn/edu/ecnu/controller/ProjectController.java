package cn.edu.ecnu.controller;

import cn.edu.ecnu.domain.Project;
import cn.edu.ecnu.domain.Teacher;
import cn.edu.ecnu.domain.User;
import cn.edu.ecnu.service.IProjectService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.jdbc.ha.LoadBalancedMySQLConnection;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.plugin2.jvm.ProxyJVMLauncher;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Api(tags = "项目信息控制器")
@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private IProjectService projectService;

    @ApiOperation("根据pid查询项目信息")
    @GetMapping("/{pid}")
    @ResponseBody
    public JSONObject findProjectById(@PathVariable String pid) {
        Project project = projectService.findProjectById(pid);
        return (JSONObject) JSON.toJSON(project);
    }

    @ApiOperation("项目的模糊查询")
    @GetMapping("/search/fuzzyQuery")
    public JSONArray fuzzyQueryForProjects(@RequestParam(required = false) String pid,
                                           @RequestParam(required = false) String projectname,
                                           @RequestParam(required = false) String type,
                                           @RequestParam(required = false) Integer stage) {
        Project project = new Project();
        project.setPid(pid).setProjectname(projectname).setStage(stage).setType(type);
        List<Project> projects = projectService.fuzzyQueryForProjects(project);
        return JSONArray.parseArray(JSON.toJSONString(projects));
    }

    @ApiOperation("创建新项目")
    @PostMapping
    @ResponseBody
    public JSONObject createProject(@RequestBody Project project) {
        String pid = "P" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        project.setPid(pid);
        project.setStage(1);
        project.setType("未评定");
        project = projectService.insertProject(project);
        return (JSONObject) JSON.toJSON(project);
    }

    @ApiOperation("指导老师项目确认")
    @PostMapping("/inst/{pid}")
    public JSONObject insConfirmProject(@PathVariable String pid) {
        Project project = new Project();
        project.setPid(pid);
        project.setStage(2);
        projectService.updateProject(project);
        return (JSONObject) JSON.toJSON(project);
    }

    @ApiOperation("管理员项目确认")
    @PostMapping("/judge/{pid}")
    public JSONObject judgeConfirmProject(@PathVariable String pid, @RequestBody Project project) {
        project.setPid(pid);
        project.setStage(4);
        projectService.updateProject(project);
        return (JSONObject) JSON.toJSON(project);
    }

    @ApiOperation("通过 sid 查询返回学生有的项目")
    @GetMapping("/stu/{sid}")
    @ResponseBody
    public JSONArray findProjectsBySid(@PathVariable String sid) {
        List<Project> projects = projectService.findProjectsBySid(sid);
        return JSONArray.parseArray(JSON.toJSONString(projects));
    }

    @ApiOperation("根据 pid 修改项目信息")
    @PutMapping
    public JSONObject updateProjectByPid(@RequestBody Project project) {
        projectService.updateProject(project);
        System.out.println(project);
        return (JSONObject) JSON.toJSON(project);
}

    @ApiOperation("查询所有项目")
    @GetMapping
    public JSONArray findAllProjects() {
        List<Project> projects = projectService.findAllProjects();
        return JSONArray.parseArray(JSON.toJSONString(projects));
    }

    @ApiOperation("查询处于第二阶段的，即需要管理员确认的项目")
    @GetMapping("/search/confirm")
    public JSONArray findProjectsWithStageEquelTwo() {
        List<Project> projects = projectService.findProjectsWithStageEqualsTwo();
        for (int i = projects.size() - 1; i >= 0; i--) {
            Project project = projects.get(i);
            if (project.getJudges().size() == 3) {
                projects.remove(project);
            }
        }
        return JSONArray.parseArray(JSON.toJSONString(projects));
    }

    @ApiOperation("查询通过了中期答辩，需要被给予 type 的项目")
    @GetMapping("/search/type")
    public JSONArray findProjectsNeedsType() {
        List<Project> projects = projectService.findProjectsNeedsType();
        return JSONArray.parseArray(JSON.toJSONString(projects));
    }


}
