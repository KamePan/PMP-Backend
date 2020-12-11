package cn.edu.ecnu.controller;

import cn.edu.ecnu.domain.Teacher;
import cn.edu.ecnu.service.TeacherService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("教师信息存储")
@RequestMapping("/teacher")
@RestController
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @ApiOperation("根据tid查找教师信息")
    @GetMapping("/{tid}")
    @ResponseBody
    public JSONObject findTeacherById(@PathVariable String tid) {
        JSONObject object = new JSONObject();
        Teacher teacher = teacherService.findTeacherById(tid);
        object.put("teacher", teacher);
        return object;
    }
}