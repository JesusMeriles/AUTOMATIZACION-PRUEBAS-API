package com.todoly.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProjectRequest {

    @JsonProperty("Content")
    private String content;

    @JsonProperty("Icon")
    private Integer icon;

    public ProjectRequest() {
    }

    public ProjectRequest(String content) {
        this.content = content;
    }

    public ProjectRequest(String content, Integer icon) {
        this.content = content;
        this.icon = icon;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getIcon() {
        return icon;
    }

    public void setIcon(Integer icon) {
        this.icon = icon;
    }
}
