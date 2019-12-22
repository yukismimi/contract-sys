package com.yukismimi.demo.role;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Data
@ToString
public class Role {

    @Id
    private long id;

    private int accessLevel;

    private String description;
}
