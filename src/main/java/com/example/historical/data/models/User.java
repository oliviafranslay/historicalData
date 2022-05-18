package com.example.historical.data.models;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Cacheable
public class User {
    @Id
    @GeneratedValue(generator = "optimized-sequence")

    private int id;

    @NotNull
    private String username;
    @NotNull
    private String password;

    public User()
    {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }

}
