package com.search.place.application.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@Entity
@Getter
@Setter
@RedisHash("user")
public class User implements Serializable {

    @Id
    private String id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private int active;

    private String roles = "";

    private String permissions = "";

    @Builder
    public User(String username, String password, String roles, String permissions){
        this.id = username;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.permissions = permissions;

        this.active = 1;
    }

    protected User() { }

    public List<String> getRoleList(){
        if(this.roles.length()>0){
            return Arrays.asList(this.roles.split(","));
        }

        return new ArrayList<>();
    }

    public List<String> getPermissionList(){
        if(this.permissions.length()>0){
            return Arrays.asList(this.permissions.split(","));
        }

        return new ArrayList<>();
    }
}