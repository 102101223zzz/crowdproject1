package com.kknb.crowdproject;

import com.kknb.crowdproject.util.PasswordUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CrowdprojectApplicationTests {

    @Test
    void contextLoads() {
        PasswordUtils passwordUtils=new PasswordUtils();
        System.out.println(passwordUtils.encrypt("passwordzzz","1234"));;
    }

}
