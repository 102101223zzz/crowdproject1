package com.kknb.crowdproject.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.kknb.crowdproject.service.userService;
import com.kknb.crowdproject.pojo.Project;
import com.kknb.crowdproject.pojo.User;
import com.kknb.crowdproject.service.projectService;
import com.kknb.crowdproject.service.userServiceimpl;
import com.kknb.crowdproject.util.fileutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/project")
public class projectControl {
    @Autowired
    @Qualifier("projectServiceimpl")
    private projectService projectService;
    //《提出众筹者》
    //增加一个新项目
    @Autowired
    @Qualifier("userServiceimpl")
   private userService userService;
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public Map<String,Object> addProject(@RequestParam(value = "file",required = false) MultipartFile file,Project project) throws IOException {
System.out.println(project);
        BASE64Encoder encoder=new BASE64Encoder();
        System.out.println(file.isEmpty());
        Map<String,Object> map=new HashMap<>();
        if(file.isEmpty())
        {
            System.out.println("seq"
            );
        }
        String image1="";
        if(!file.isEmpty())
        {
            image1=encoder.encode(file.getBytes());
        }
        User user=userService.selectUser(project.getCreatorID());
        if(projectService.selectOneProjectByID(project.getProjectID()).getProjectID()==project.getProjectID())
        {
        }
        project.setProjectID(project.getProjectID()+1);
        user.setProjectNum(user.getProjectNum()+1);
        project.setProjectMaterials(image1);
        projectService.addProject(project);
        map.put("msg","添加项目成功，等待审核");
        return map;
    }
    //查询审核状态
    @RequestMapping("/selectselfsProjectByID")
    List<Project> selectselfsProjectByID(@RequestParam int userId)
    {
        System.out.println(userId);
        return projectService.selectselfsProjectByID(userId);
    }
    //查询筹资情况
    @RequestMapping("/selectMoney")
    int selectMoney(int projectId)
    {
        return projectService.selectMoney(projectId);
    }


    //《一般用户》
    //搜索所有项目
    @RequestMapping("/selectAllProject")
    public List<Project >  selectAllProject()
    {
        return projectService.selectAllProject();
    }
    //投资
    @RequestMapping("/addMoney")
    public Map<String, Object> addMoney(@RequestParam int projectId, @RequestParam int money)
    {
        return projectService.addMoney(projectId,money);
    }

    //根据id查找一个用户
    @RequestMapping("/selectOneProjectByID")
    Project selectOneProjectByID(@RequestParam int projectId)
    {
        return projectService.selectOneProjectByID(projectId);
    }


    //《管理员》
    // 查找未审核的项目;
    @RequestMapping("/selectAllNotPassProject")
    List<Project> selectAllNotPassProject()
    {
     return  projectService.selectAllNotPassProject();
    }
    //删除一个项目
    @RequestMapping("/deleteProjectByID")
    int deleteProjectByID(@RequestParam int projectid)
    {
        return projectService.deleteProjectByID(projectid);
    }
    //通过审核
    @RequestMapping("/changeAudi")
    int changeAudi(@RequestParam(value = "projectId") int projectId)
    {
        return projectService.changeAudi(projectId,1);
    }
    @RequestMapping("/detail")
    public Map<String,Object> decodeproject(int projectId) throws IOException {
        System.out.println(projectId);
        Map<String,Object> map=new HashMap<>();
        fileutil fileutil=new fileutil();
        Project project=projectService.selectOneProjectByID(projectId);
         System.out.println(project);
        byte[] bytes=(byte[])project.getProjectMaterials();
   User user=userService.selectUser(project.getCreatorID());
   String telephone=user.getContactInformation();
        bytes=fileutil.fileUtil(bytes);
        project.setProjectMaterials(bytes);
        map.put("Project",project);
       map.put("telephone",telephone);
       return map;
    }

}

