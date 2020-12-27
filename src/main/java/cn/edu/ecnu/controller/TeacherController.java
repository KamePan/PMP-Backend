package cn.edu.ecnu.controller;

import cn.edu.ecnu.domain.Teacher;
import cn.edu.ecnu.service.ITeacherService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "教师信息控制器")
@RequestMapping("/teacher")
@RestController
public class TeacherController {

    @Autowired
    private ITeacherService teacherService;

    @ApiOperation("根据 tid 查找教师信息")
    @GetMapping("/{tid}")
    @ResponseBody
    public JSONObject findTeacherById(@PathVariable String tid) {
        Teacher teacher = teacherService.findTeacherById(tid);
        return (JSONObject) JSON.toJSON(teacher);
    }

    @ApiOperation("根据 tid 修改教师信息")
    @PutMapping
    @ResponseBody
    public JSONObject UpdateTeacherById(@RequestBody Teacher teacher) {
        JSONObject object = new JSONObject();
        Teacher newTeacher = teacherService.updateTeacherSelective(teacher);
        object.put("teacher", newTeacher);
        return object;
    }


}
