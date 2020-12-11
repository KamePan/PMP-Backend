package cn.edu.ecnu.service;

import cn.edu.ecnu.dao.ProjectMapper;
import cn.edu.ecnu.domain.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    public Project findProjectById(String pid) {
        Project project = projectMapper.selectByPrimaryKey(pid);
        return project;
    }

}
