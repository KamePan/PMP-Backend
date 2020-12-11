package cn.edu.ecnu.service;

import cn.edu.ecnu.dao.TeacherMapper;
import cn.edu.ecnu.domain.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    public Teacher findTeacherById(String tid) {
        Teacher teacher = teacherMapper.selectByPrimaryKey(tid);
        return teacher;
    }
}
