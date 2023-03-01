package com.kknb.crowdproject.mapper;

import com.kknb.crowdproject.pojo.Project;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface projectMapper {

    //《提出众筹者》
   //增加一个新项目
    int addProject(Project project);
    //查询审核状态
   List<Project> selectselfsProjectByID(int userId);
    //查看筹资情况
     int selectMoney(int projectId);
    //《一般用户》
    //搜索所有项目
   List<Project> selectAllProject();
    //根据id查找一个用户
    Project selectOneProjectByID(int projectId);
   //《管理员》
   //删除项目
     int deleteProjectByID(int projectId);
     //查找未审核项目
    List<Project> selectAllNotPassProject();//查找未审核的项目;
    //通过审核
    int updateAudit(int projectID,int isAudit);
    //查找一个未审核项目
    Project selectOneNotPassProjectByID(int projectId);
    //更新当前资金
    int updateNowMoney(int money,int projectID);
}
