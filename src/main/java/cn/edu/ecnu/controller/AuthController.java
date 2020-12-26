package cn.edu.ecnu.controller;

import cn.edu.ecnu.domain.User;
import cn.edu.ecnu.service.IUserService;
import cn.edu.ecnu.service.UserService;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@Api(tags = "用户验证与鉴权控制器")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IUserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /*先加密再放入数据库*/
    @PostMapping("/register")
    public String registerUser(@RequestBody Map<String, String> registerUser) {
        User user = new User();
        user.setUid("U" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        user.setUsername(registerUser.get("username"));
        user.setPassword(bCryptPasswordEncoder.encode(registerUser.get("password")));
        user.setRole("ROLE_STUDENT");
        User savedUser = userService.registerUser(user);
        System.out.println(savedUser.toString());
        return savedUser.toString();
    }

    /*@GetMapping("/login")
    public JSONObject userlogin(@RequestBody User user) {
        JSONObject object = new JSONObject();
        User loginUser = userService.findUserByUsername();
        object.put("user", user);
        return object;
    }*/
}
