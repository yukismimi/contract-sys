package com.yukismimi.demo.receipt;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table
@Data
@ToString
public class Receipt {

    @Id
    @GeneratedValue
    private long id;

    //发票抬头
    private String title;

    //时间
    private Timestamp timestamp;

    //消费
    private long consume;
}
