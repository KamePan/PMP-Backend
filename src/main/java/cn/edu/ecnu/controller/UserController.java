package cn.edu.ecnu.controller;

import cn.edu.ecnu.domain.User;
import cn.edu.ecnu.service.UserService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "用户信息控制器")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    public UserService userService;

    @ApiOperation(value = "查找所有用户", notes = "可以查找到所有用户")
    @ResponseBody
    @RequestMapping("/findUsers")
    public JSONObject findAllUsers() {
        List<User> users = userService.getAllUsers();
        JSONObject object = new JSONObject();
        object.put("userList",users);
        return object;
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JSONObject findUserById(@RequestBody User user) {
        JSONObject object = new JSONObject();
        User user1 = userService.findUserByUsername(user);
        return object;
    }

    @GetMapping("/getMessage")
    public String getMessage(){
        return "你已通过验证";
    }

}
