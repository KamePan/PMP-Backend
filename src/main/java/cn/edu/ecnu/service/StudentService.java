package cn.edu.ecnu.service;

import cn.edu.ecnu.dao.StudentMapper;
import cn.edu.ecnu.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

@CacheConfig(cacheNames = "Student", keyGenerator = "keyGenerator")
//@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Cacheable
    public Student findStudentById(String id) {
        Student student = studentMapper.selectByPrimaryKey(id);
        return student;
    }
}
