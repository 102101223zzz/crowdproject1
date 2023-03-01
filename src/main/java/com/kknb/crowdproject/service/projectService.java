package com.kknb.crowdproject.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.kknb.crowdproject.pojo.Project;

import java.util.List;
import java.util.Map;

public interface projectService {
    //《提出众筹者》
    //增加一个新项目
    public  boolean addProject(Project project);
    //查询审核状态
    List<Project> selectselfsProjectByID(int userId);
    //查询筹资情况
    int selectMoney(int projectId);

    //《一般用户》
    //搜索所有项目
    public List<Project>  selectAllProject();

   //投资
   public Map<String,Object> addMoney(int money, int projectID);
    //根据id查找一个用户
    Project selectOneProjectByID(int projectid);

    //《管理员》
    // 查找未审核的项目;
    List<Project>  selectAllNotPassProject();
//删除一个项目
    int deleteProjectByID(int projectid);
    //通过审核
    int changeAudi(int projectId,int isAudit);


}
