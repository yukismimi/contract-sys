package com.yukismimi.demo.admin;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Data
@ToString
public class Admin {

    @Id
    @GeneratedValue
    private long id;

    private String nickname;

    private String password;
}
