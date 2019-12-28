package com.yukismimi.demo.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table
@Data
@ToString
public class Contract {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    //合同名
    private String contractName;

    //合同内容
    private String content;

    //甲方
    private String firstParty;

    //乙方
    private String secondParty;

    //到期时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dueTime;

    private boolean willExpire;
}
