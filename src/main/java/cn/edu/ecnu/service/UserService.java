package cn.edu.ecnu.service;

import cn.edu.ecnu.dao.StudentMapper;
import cn.edu.ecnu.dao.TeacherMapper;
import cn.edu.ecnu.dao.UserMapper;
import cn.edu.ecnu.domain.Student;
import cn.edu.ecnu.domain.Teacher;
import cn.edu.ecnu.domain.User;
import cn.edu.ecnu.domain.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public List<User> getAllUsers() {
        List<User> users = null;
        UserExample example = new UserExample();
        users = userMapper.selectByExample(example);
        return users;
    }

    public User findUserByUsername(User user) {
        return null;
    }

    public User registerUser(User user) {
        userMapper.insertSelective(user);
        return user;
    }

    public void insertUserForTeacher(User user) {
        Teacher teacher = new Teacher();
        teacher.setTid(user.getUid());
        teacherMapper.insertSelective(teacher);
        userMapper.insertSelective(user);
    }
}
