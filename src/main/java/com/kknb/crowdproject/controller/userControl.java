package com.kknb.crowdproject.controller;

import cn.dev33.satoken.context.model.SaRequest;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.kknb.crowdproject.pojo.User;
import com.kknb.crowdproject.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class userControl {
    @Autowired
    private userService userService;
    @RequestMapping(value = "/login")
    public SaResult doLogin(int userId, String password) {

        System.out.println(userId+"============="+password);
        User user=userService.selectUser(userId);
        System.out.println(user);
        if(user.getUserPassword().equals(password)) {
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
        System.out.println(user.toString());
        if (userService.selectUser(user.getUserID()) == null) {
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
