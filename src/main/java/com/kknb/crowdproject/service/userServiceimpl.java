package com.kknb.crowdproject.service;

import com.kknb.crowdproject.mapper.userMapper;
import com.kknb.crowdproject.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class userServiceimpl implements userService{

    @Autowired
    private userMapper userMapper;


    public void setUserMapper(userMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public int addUser(User user)
    {
       /* if(selectUser(User.getUserID())==null)//代表没有人注册过这个账号;
        {

        }*/  userMapper.addUser(user);
        return 1;

    }
    @Override
    public User selectUser(int userID)
    {
       return userMapper.selectUser(userID);
    }


    @Override
    public List<User> selectAllUser()
    {
        return userMapper.selectAllUser();
    }



}
