package cn.edu.ecnu.service;

import cn.edu.ecnu.dao.UserMapper;
import cn.edu.ecnu.domain.User;
import cn.edu.ecnu.domain.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> getAllUsers() {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo("pkm");
        List<User> users = userMapper.selectByExample(example);
        System.out.println(users.toString());
        return users;
    }

    public User findUserByUsername(User user) {
        return null;
    }

    public User registerUser(User user) {
        userMapper.insertSelective(user);
        return user;
    }
}
