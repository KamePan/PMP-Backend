package cn.edu.ecnu.controller;

import cn.edu.ecnu.domain.Project;
import cn.edu.ecnu.domain.Teacher;
import cn.edu.ecnu.domain.User;
import cn.edu.ecnu.service.ITeacherService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Api(tags = "教师信息控制器")
@RequestMapping("/teacher")
@RestController
public class TeacherController {

    @Autowired
    private ITeacherService teacherService;

    @ApiOperation("查找所有教师信息")
    @GetMapping
    @ResponseBody
    public JSONArray findAllTeachers() {
        List<Teacher> teachers = teacherService.findAllTeachers();
        return JSONArray.parseArray(JSON.toJSONString(teachers));
    }

    @ApiOperation("根据 username 查询教师信息")
    @GetMapping("/username/{username}")
    public JSONObject findTeacherByUsername(@PathVariable String username) {
        Teacher teacher = teacherService.findTeacherByUsername(username);
        return (JSONObject) JSON.toJSON(teacher);
    }

    @ApiOperation("根据 tid 查找教师信息")
    @GetMapping("/{tid}")
    @ResponseBody
    public JSONObject findTeacherById(@PathVariable String tid) {
        Teacher teacher = teacherService.findTeacherById(tid);
        return (JSONObject) JSON.toJSON(teacher);
    }

    @ApiOperation("教师的模糊查询")
    @GetMapping("/search/fuzzyQuery")
    public JSONArray fuzzyQueryForTeacher(@RequestParam(required = false) String tid,
                                           @RequestParam(required = false) String username,
                                           @RequestParam(required = false) String department) {
        Teacher teacher = new Teacher().setTid(tid).setUsername(username).setDepartment(department);
        List<Teacher> teachers = teacherService.fuzzyQueryForTeachers(teacher);
        return JSONArray.parseArray(JSON.toJSONString(teachers));
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
