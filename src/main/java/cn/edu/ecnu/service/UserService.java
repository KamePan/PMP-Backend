package cn.edu.ecnu.service;

import cn.edu.ecnu.dao.JudgeTeacherProjectMapper;
import cn.edu.ecnu.dao.StudentMapper;
import cn.edu.ecnu.dao.TeacherMapper;
import cn.edu.ecnu.dao.UserMapper;
import cn.edu.ecnu.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@CacheConfig(cacheNames = "User", keyGenerator = "keyGenerator")
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    JudgeTeacherProjectMapper judgeTeacherProjectMapper;

    @Cacheable
    public List<User> getAllUsers() {
        List<User> users = null;
        UserExample example = new UserExample();
        users = userMapper.selectByExample(example);
        return users;
    }

    public User registerUser(User user) {
        Student student = new Student();
        student.setSid(user.getUid());
        studentMapper.insertSelective(student);
        userMapper.insertSelective(user);
        return user;
    }

    public void insertUserForTeacher(User user) {
        Teacher teacher = new Teacher();
        teacher.setTid(user.getUid());
        teacherMapper.insertSelective(teacher);
        userMapper.insertSelective(user);
    }

    public void insertJudgeTeacherProject(JudgeTeacherProject judge) {
        insertJudgeTeacherProject(judge);
    }
}
