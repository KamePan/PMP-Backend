package cn.edu.ecnu.backend;

import cn.edu.ecnu.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

@SpringBootTest

@ComponentScan(basePackages = {"/"})
class BackendApplicationTests {

    //@Autowired
    //private UserService userService;

    @Test
    void contextLoads() {
        //System.out.println(userService.getAllUsers().toString());

    }

}
