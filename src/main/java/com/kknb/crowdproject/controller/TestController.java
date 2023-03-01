package com.kknb.crowdproject.controller;


import com.kknb.crowdproject.service.userServiceimpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/test")
    public String test(){
        userServiceimpl userServiceimpl=new userServiceimpl();
        System.out.println(userServiceimpl.selectUser(666666));;
        return "hello";
    }
}
