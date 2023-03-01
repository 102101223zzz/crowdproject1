package com.kknb.crowdproject.mapper;

import com.kknb.crowdproject.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface userMapper{


       public  int addUser(User user);

       public User selectUser(int userID);
       public List<User> selectAllUser();
       int telephoneNum(int userID);
       int projectNum(int projectNum,int userID);

}
