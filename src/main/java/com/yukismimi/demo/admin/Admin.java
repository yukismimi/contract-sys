package com.yukismimi.demo.admin;

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
public class Admin {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    private String nickname;

    private String password;
}
