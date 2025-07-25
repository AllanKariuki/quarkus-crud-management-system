package org.acme.dto;

import java.time.LocalDateTime;

import org.acme.entity.User;

public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructors
    public UserResponseDTO() {}

    public UserResponseDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.createdAt = user.getCreatedAt();
        this.updatedAt = user.getUpdateAt();
    }

    public Long getId() { return id;}
    public void setId(Long id) {this.id = id;}
    
    public String getName() { return name;}
    public void setName(String name) { this.name = name;}

    public String getEmail() { return email;}
    public void setEmail(String email) { this.email = email;}

    public LocalDateTime getCreatedAt() { return createdAt;}
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdateAt() { return updatedAt;}
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
 