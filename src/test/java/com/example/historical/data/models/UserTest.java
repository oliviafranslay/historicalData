package com.example.historical.data.models;

public class UserTest {

    public static User.UserBuilder dummy() {
        return User.builder()
                .id(1)
                .username("test")
                .password("testing");
    }
}
