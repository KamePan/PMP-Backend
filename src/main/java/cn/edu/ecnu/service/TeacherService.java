package cn.edu.ecnu.service;

import cn.edu.ecnu.dao.TeacherMapper;
import cn.edu.ecnu.domain.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames = "Teacher", keyGenerator = "keyGenerator")
@Service
public class TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Cacheable
    public Teacher findTeacherById(String tid) {
        Teacher teacher = teacherMapper.selectByPrimaryKey(tid);;
        return teacher;
    }
}
