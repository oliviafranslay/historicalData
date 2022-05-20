package com.example.historical.data.models;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "users")
@Cacheable
public class User {
    @Id
    @GeneratedValue(generator = "optimized-sequence")

    private int id;

    @NotEmpty(message = "Username cannot be empty")
    @Column(name = "username", unique = true)
    private String username;
    @NotEmpty(message = "Password cannot be empty")
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
