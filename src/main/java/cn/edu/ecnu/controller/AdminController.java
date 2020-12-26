package cn.edu.ecnu.controller;

import cn.edu.ecnu.domain.JudgeTeacherProject;
import cn.edu.ecnu.domain.User;
import cn.edu.ecnu.service.IUserService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Api(tags = "用户信息控制器")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public IUserService userService;

    @ApiOperation(value = "查找所有用户")
    @ResponseBody
    @GetMapping
    public JSONObject findAllUsers() {
        List<User> users = userService.getAllUsers();
        JSONObject object = new JSONObject();
        object.put("userList",users);
        return object;
    }

    @ApiOperation(value = "分配项目审查老师")
    @ResponseBody
    @PostMapping("/assignTeacher")
    public JSONObject assignJudgeForProject(@RequestBody JudgeTeacherProject judge) {
        JSONObject object = new JSONObject();
        userService.insertJudgeTeacherProject(judge);
        object.put("judge",judge);
        return object;
    }

    @ApiOperation("创建权限为 ROLE_TEACHER 的用户")
    @ResponseBody
    @PostMapping("createTeacher")
    public JSONObject createUserForTeacher(@RequestBody User user) {
        JSONObject object = new JSONObject();
        user.setUid("U" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_TEACHER");
        userService.insertUserForTeacher(user);
        object.put("user", user);
        return object;
    }

}
