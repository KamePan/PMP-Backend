package cn.edu.ecnu.controller;

import cn.edu.ecnu.domain.Project;
import cn.edu.ecnu.domain.Teacher;
import cn.edu.ecnu.service.IProjectService;
import cn.edu.ecnu.service.ProjectService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @ApiOperation("创建新项目")
    @PostMapping
    @ResponseBody
    public JSONObject createProject(@RequestBody Project project) {
        String pid = "P" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        project.setPid(pid);
        project.setStage(1);
        project = projectService.insertProject(project);
        return (JSONObject) JSON.toJSON(project);
    }

    @ApiOperation("通过 sid 查询返回学生有的项目")
    @GetMapping("/stu/{sid}")
    @ResponseBody
    public JSONObject findProjectsBySid(@PathVariable String sid) {
        JSONObject object = new JSONObject();
        List<Project> projects = projectService.findProjectsBySid(sid);
        object.put("projects", projects);
        return object;
    }

}
