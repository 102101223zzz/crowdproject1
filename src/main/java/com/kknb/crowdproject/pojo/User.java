package com.kknb.crowdproject.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer userID;
    private String userName;
    private String userPassword;
    private String contactInformation;
    private int userRight;//user为普通人员，admin为管理员
    private int projectNum;
}

