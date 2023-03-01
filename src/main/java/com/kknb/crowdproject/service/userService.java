package com.kknb.crowdproject.service;

import com.kknb.crowdproject.pojo.User;

import java.util.List;

public interface userService {


   public int addUser(User user);
    public User selectUser(int userID);
    public List<User> selectAllUser();
    int updateProjectNum(int projectNum,int userID);
}
