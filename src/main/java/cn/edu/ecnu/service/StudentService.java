package cn.edu.ecnu.service;

import cn.edu.ecnu.dao.StudentMapper;
import cn.edu.ecnu.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public Student findStudentById(String id) {
        Student student = null;
        if (redisTemplate.hasKey(id)) {
            student = (Student) redisTemplate.opsForValue().get(id);
        } else {
            student = studentMapper.selectByPrimaryKey(id);
            redisTemplate.opsForValue().set(id, student);
        }
        return student;
    }
}
