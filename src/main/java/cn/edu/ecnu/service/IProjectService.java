package cn.edu.ecnu.service;

import cn.edu.ecnu.domain.Project;

public interface IProjectService {

    public void insertProject(Project project);

    public Project findProjectById(String pid);
}
