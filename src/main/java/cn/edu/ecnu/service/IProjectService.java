package cn.edu.ecnu.service;

import cn.edu.ecnu.domain.Project;

import java.util.List;

public interface IProjectService {

    Project insertProject(Project project);

    Project findProjectById(String pid);

    List<Project> findProjectsBySid(String sid);
}
