package cn.edu.ecnu.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

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
