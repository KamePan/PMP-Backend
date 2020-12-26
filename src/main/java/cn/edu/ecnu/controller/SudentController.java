package cn.edu.ecnu.controller;

import cn.edu.ecnu.domain.Student;
import cn.edu.ecnu.service.IStudentService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "学生信息控制器")
@RestController
@RequestMapping("/student")
public class SudentController {

    @Autowired
    private IStudentService studentService;

    @ApiOperation("通过用户id查询学生信息")
    @GetMapping("/{id}")
    @ResponseBody
    public JSONObject findStudentById(@PathVariable("id") String id) {
        JSONObject object = new JSONObject();
        Student stu = studentService.findStudentById(id);
        object.put("stu", stu);
        return object;
    }
}
