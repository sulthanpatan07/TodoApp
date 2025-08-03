package com.project.Todo.Model;

public class UserDTO {
    private Long id;
    private String username;

    public UserDTO() {}

    public UserDTO(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
