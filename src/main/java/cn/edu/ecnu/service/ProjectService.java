package cn.edu.ecnu.service;

import cn.edu.ecnu.dao.ProjectMapper;
import cn.edu.ecnu.domain.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames = "Project", keyGenerator = "keyGenerator")
@Service
public class ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Cacheable
    public Project findProjectById(String pid) {
        Project project = null;
        if (redisTemplate.hasKey(pid)) {
            project = (Project) redisTemplate.opsForValue().get(pid);
            System.out.println("redis: " + project.toString());
        } else {
            project = projectMapper.selectByPrimaryKey(pid);
            redisTemplate.opsForValue().set(pid, project);
        }
        return project;
    }

    public void insertProject(Project project) {
        projectMapper.insertSelective(project);
    }

}
