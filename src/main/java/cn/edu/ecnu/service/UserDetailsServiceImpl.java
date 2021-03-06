package cn.edu.ecnu.service;

import cn.edu.ecnu.dao.UserMapper;
import cn.edu.ecnu.domain.JwtUser;
import cn.edu.ecnu.domain.User;
import cn.edu.ecnu.domain.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(s);
        List<User> users = userMapper.selectByExample(example);
        User user = null;
        if (users.size() > 0) {
            user = users.get(0);
        }
        return new JwtUser(user);
    }
}
