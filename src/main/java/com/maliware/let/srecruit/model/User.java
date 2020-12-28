package com.maliware.let.srecruit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "users")
public class User implements Serializable {
//    @Column(unique = true) spring security specific column names
//    private String email;  as well as table name
    @Id
    private UUID uuid;
    @Column(name = "username", unique = true)
    private String username;
    private String password;
    private Boolean enabled = true;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    List<UserAuthority> userAuthorities = new ArrayList<>();

    public User(UUID userId, String name, String pwd, boolean b) {
        this.uuid = userId;
        this.username = name;
        this.password = pwd;
        this.enabled = b;
    }

    public User(User user) {
        this.uuid = user.uuid;
        this.username = user.username;
        this.password = user.password;
        this.enabled = user.enabled;
        this.userAuthorities = new ArrayList<>(user.userAuthorities);
    }

    //Helper method
    public void addAuthority(String authority){
        this.userAuthorities.add(new UserAuthority(this,authority));
    }
}
