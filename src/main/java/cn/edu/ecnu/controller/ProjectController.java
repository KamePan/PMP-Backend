package cn.edu.ecnu.controller;

import cn.edu.ecnu.domain.Project;
import cn.edu.ecnu.service.ProjectService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("项目信息控制器")
@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @ApiOperation("根据pid查询项目信息")
    @GetMapping("/{pid}")
    @ResponseBody
    public JSONObject findProjectById(@PathVariable String pid) {
        JSONObject object = new JSONObject();
        Project project = projectService.findProjectById(pid);
        object.put("pro", project);
        return object;
    }

}
