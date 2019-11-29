package com.yukismimi.demo.user;

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
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    private String name;

    private String phone;
}
