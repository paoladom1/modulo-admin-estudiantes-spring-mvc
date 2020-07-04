package com.uca.capas.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "public", name = "authorities")
public class Authorities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authority_id")
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "authority")
    private String authority;

    public Authorities(String username, String authority) {
        this.username = username;
        this.authority = authority;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
