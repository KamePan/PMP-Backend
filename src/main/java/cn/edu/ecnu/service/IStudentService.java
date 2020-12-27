package cn.edu.ecnu.service;

import cn.edu.ecnu.domain.Student;

public interface IStudentService {

    Student findStudentById(String id);

    Student findUserByUsername(String username);
}
