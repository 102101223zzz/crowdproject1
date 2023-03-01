package com.kknb.crowdproject.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    private String projectName;
    private Integer projectID;
    private Integer creatorID;
    private String creatorName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date submissionTime;
    private Object projectMaterials;
    private String remarks;
    private  String purpose;
    private int nowmoney;//当前资金
    private int isAudit;//是否审核；
    private int targetmoney;
    private String region;
    private String address;


}
