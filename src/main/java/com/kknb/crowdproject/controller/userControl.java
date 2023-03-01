package com.kknb.crowdproject.controller;

import cn.dev33.satoken.context.model.SaRequest;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.kknb.crowdproject.pojo.User;
import com.kknb.crowdproject.service.userService;
import com.kknb.crowdproject.util.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(originPatterns = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class userControl {
    @Autowired
    private userService userService;
    @RequestMapping(value = "/login")
    public SaResult doLogin(int userId, String password) {

        System.out.println(userId+"============="+password);
        PasswordUtils passwordUtils=new PasswordUtils();

        User user=userService.selectUser(userId);
        System.out.println(user);
        String password1=passwordUtils.decrypt("passwordzzz", user.getUserPassword());
        System.out.println(password1);
        if(password1.equals(password)) {
            StpUtil.login(userId);

            SaTokenInfo info = StpUtil.getTokenInfo();

            StpUtil.login(userId);

            info.setTag(user.getUserRight()+"");

            return SaResult.data(info);
        }
        return SaResult.error("账号或密码错误");

    }

    @RequestMapping("/enroll")
    public Map<String, Object> enroll(@RequestBody User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        PasswordUtils passwordUtils=new PasswordUtils();
        System.out.println(user.toString());
        if (userService.selectUser(user.getUserID()) == null) {
            String password=passwordUtils.encrypt("passwordzzz",user.getUserPassword());
            user.setUserPassword(password);
            userService.addUser(user);
            map.put("code", 1);
            map.put("msg", "注册成功!");
        } else {
            map.put("msg", "注册失败，该用户已存在，请重新输入!");
        }
        return map;
    }

@RequestMapping("/qweq")
    public String qweq() {
        return "aeq";
    }
}
