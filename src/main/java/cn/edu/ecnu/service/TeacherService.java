package cn.edu.ecnu.service;

import cn.edu.ecnu.dao.TeacherMapper;
import cn.edu.ecnu.domain.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public Teacher findTeacherById(String tid) {
        Teacher teacher = null;
        if (redisTemplate.hasKey(tid)) {
            teacher = (Teacher) redisTemplate.opsForValue().get(tid);
        } else {
            teacher = teacherMapper.selectByPrimaryKey(tid);
            redisTemplate.opsForValue().set(tid, teacher);
        }
        return teacher;
    }
}
