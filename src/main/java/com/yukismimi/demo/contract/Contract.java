package com.yukismimi.demo.contract;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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
    private String title;

    //合同内容
    private String content;

    //甲方
    private String firstParty;

    //乙方
    private String secondParty;
}
