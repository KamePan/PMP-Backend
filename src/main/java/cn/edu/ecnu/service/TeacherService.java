package cn.edu.ecnu.service;

import cn.edu.ecnu.dao.TeacherMapper;
import cn.edu.ecnu.domain.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

@CacheConfig(cacheNames = "Teacher")
//@Service
public class TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Cacheable(keyGenerator = "keyGenerator")
    public Teacher findTeacherById(String tid) {
        Teacher teacher = teacherMapper.selectByPrimaryKey(tid);;
        return teacher;
    }

    @CachePut(key = "targetClass + #p0.tid")
    public Teacher updateTeacherSelective(Teacher teacher) {
        teacherMapper.updateByPrimaryKeySelective(teacher);
        Teacher newTeacher = teacherMapper.selectByPrimaryKey(teacher.getTid());
        return newTeacher;
    }


}
