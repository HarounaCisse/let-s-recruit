package com.maliware.let.srecruit.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "authorities")
public class UserAuthority {
    @Id
    private UUID id;
    private String authority;
    @ManyToOne
//    @JoinColumn(name = "username", referencedColumnName = "username")
    User user;

    public UserAuthority(User user, String authority) {
        this.id = UUID.randomUUID();
        this.user = user;
        this.authority = authority;
    }
}
