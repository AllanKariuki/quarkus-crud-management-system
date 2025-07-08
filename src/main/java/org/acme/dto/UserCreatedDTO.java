package org.acme.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserCreatedDTO {
    @NotBlank(message =  "Name is required")
    private String name;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    // Constructors
    public UserCreatedDTO() {}

    public UserCreatedDTO( String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Getters adn setters
    public String getName() { return name;}
    public void setName( String name) {
        this.name = name;
    }

    public String getEmail() { return email;}
    public void setEmail(String email) {
        this.email = email;
    }
}
