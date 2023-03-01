package com.kknb.crowdproject.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.kknb.crowdproject.mapper.projectMapper;
import com.kknb.crowdproject.mapper.userMapper;
import com.kknb.crowdproject.pojo.Project;
import com.kknb.crowdproject.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service

public class projectServiceimpl implements projectService{

    @Autowired
    private  projectMapper projectMapper;

    @Autowired
    private userMapper userMapper;
    public void setProjectMapper(projectMapper projectMapper) {
        this.projectMapper = projectMapper;
    }
    //《提出众筹者》
    //增加一个新项目
    @Override
    public  boolean addProject(Project project)
    {
      if(projectMapper.selectOneProjectByID(project.getProjectID())==null)
      {
          //说明该id不存在可以增加
          projectMapper.addProject(project);
          return true;
      }
      return false;
    }
  //查找自己项目的审核状态
    public List<Project> selectselfsProjectByID(int userId)
 {
     return projectMapper.selectselfsProjectByID(userId);
 }
    //查询筹资情况
    @Override
    public int selectMoney(int projectId)
    {
        return projectMapper.selectMoney(projectId);
    }
    //《一般用户》
    // 查找所有项目
        @Override
        public  List<Project> selectAllProject()
        {
                return projectMapper.selectAllProject();

        }

    //搜索一个项目通过项目id
    @Override
    public Project selectOneProjectByID(int id)
    {
        return projectMapper.selectOneProjectByID(id);
    }

    //筹资
    @Override
    public Map<String,Object> addMoney(int projectId, int money)
    {
        Map<String,Object> map=new HashMap<>();
        Project project=selectOneProjectByID(projectId);
        if(project!=null)
        {
            map.put("msg","投资成功!");
            projectMapper.updateNowMoney(money+project.getNowmoney(),projectId);
            map.put("msg","该账户目前拥有"+selectOneProjectByID(projectId).getNowmoney()+"元");
            return map;
        }
        map.put("msg","投资失败,该项目ID输入错误!");
        return map;
    }
    //《管理员》
    //删除一个项目通过项目id
    @Override
    public   int deleteProjectByID( int projectid)
    {
        User user = userMapper.selectUser(StpUtil.getLoginIdAsInt());
        System.out.println(user.getUserRight());
        if(user.getUserRight()==1)
        {
            //管理员可以删除
            return projectMapper.deleteProjectByID(projectid);
        }
        return 0;
    }
    //搜索所有未审核项目
    @Override
    public List<Project> selectAllNotPassProject()
    {
         User user = userMapper.selectUser(StpUtil.getLoginIdAsInt());
        if(user.getUserRight()==1)
        {

            //管理员可以搜索未审核项目
           return  projectMapper.selectAllNotPassProject();

        }
        return null;
    }
    //通过审核
    @Override
    public int changeAudi(int projectId,int isAudit)
    {
        User user = userMapper.selectUser(StpUtil.getLoginIdAsInt());
        if(user.getUserRight()==1)
        {
           projectMapper.updateAudit(projectId,isAudit);
        }
        return 0;
    }


}
