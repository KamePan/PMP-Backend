package cn.edu.ecnu.service;

import cn.edu.ecnu.domain.Teacher;

public interface ITeacherService {

    public Teacher findTeacherById(String tid);

    public Teacher updateTeacherSelective(Teacher teacher);

}
