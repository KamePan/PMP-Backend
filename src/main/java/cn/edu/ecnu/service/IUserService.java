package cn.edu.ecnu.service;

import cn.edu.ecnu.domain.User;

import java.util.List;

public interface IUserService {

    public void insertUserForTeacher(User user);

    public User registerUser(User user);

    public List<User> getAllUsers();

    User findUserByUsername(String username);

    void modifyPassword(User user);
}
