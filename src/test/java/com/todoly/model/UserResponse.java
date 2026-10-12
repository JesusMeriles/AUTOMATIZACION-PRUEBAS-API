package com.todoly.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserResponse {

    @JsonProperty("Id")
    private Long id;

    @JsonProperty("Email")
    private String email;

    @JsonProperty("FullName")
    private String fullName;

    @JsonProperty("TimeZone")
    private Double timeZone;

    @JsonProperty("IsProUser")
    private Boolean isProUser;

    @JsonProperty("DefaultProjectId")
    private Long defaultProjectId;

    @JsonProperty("ErrorMessage")
    private String errorMessage;

    @JsonProperty("ErrorCode")
    private Integer errorCode;

    public UserResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(Double timeZone) {
        this.timeZone = timeZone;
    }

    public Boolean getIsProUser() {
        return isProUser;
    }

    public void setIsProUser(Boolean proUser) {
        isProUser = proUser;
    }

    public Long getDefaultProjectId() {
        return defaultProjectId;
    }

    public void setDefaultProjectId(Long defaultProjectId) {
        this.defaultProjectId = defaultProjectId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }
}
