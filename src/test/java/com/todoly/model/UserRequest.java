package com.todoly.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRequest {

    @JsonProperty("Email")
    private String email;

    @JsonProperty("FullName")
    private String fullName;

    @JsonProperty("Password")
    private String password;

    public UserRequest() {
    }

    public UserRequest(String email, String fullName, String password) {
        this.email = email;
        this.fullName = fullName;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
