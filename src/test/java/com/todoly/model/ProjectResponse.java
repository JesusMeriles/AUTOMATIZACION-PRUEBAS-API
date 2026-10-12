package com.todoly.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectResponse {

    @JsonProperty("Id")
    private Long id;

    @JsonProperty("Content")
    private String content;

    @JsonProperty("ItemsCount")
    private Integer itemsCount;

    @JsonProperty("Icon")
    private Integer icon;

    @JsonProperty("ItemType")
    private Integer itemType;

    @JsonProperty("ParentId")
    private Long parentId;

    @JsonProperty("Collapsed")
    private Boolean collapsed;

    @JsonProperty("ItemOrder")
    private Integer itemOrder;

    @JsonProperty("IsProjectShared")
    private Boolean isProjectShared;

    @JsonProperty("ProjectShareOwnerName")
    private String projectShareOwnerName;

    @JsonProperty("ProjectShareOwnerEmail")
    private String projectShareOwnerEmail;

    @JsonProperty("IsShareApproved")
    private Boolean isShareApproved;

    @JsonProperty("IsOwnProject")
    private Boolean isOwnProject;

    @JsonProperty("LastSyncedDateTime")
    private String lastSyncedDateTime;

    @JsonProperty("LastUpdatedDate")
    private String lastUpdatedDate;

    @JsonProperty("Deleted")
    private Boolean deleted;

    @JsonProperty("SyncClientCreationId")
    private String syncClientCreationId;

    @JsonProperty("ErrorMessage")
    private String errorMessage;

    @JsonProperty("ErrorCode")
    private Integer errorCode;

    public ProjectResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getItemsCount() {
        return itemsCount;
    }

    public void setItemsCount(Integer itemsCount) {
        this.itemsCount = itemsCount;
    }

    public Integer getIcon() {
        return icon;
    }

    public void setIcon(Integer icon) {
        this.icon = icon;
    }

    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Boolean getCollapsed() {
        return collapsed;
    }

    public void setCollapsed(Boolean collapsed) {
        this.collapsed = collapsed;
    }

    public Integer getItemOrder() {
        return itemOrder;
    }

    public void setItemOrder(Integer itemOrder) {
        this.itemOrder = itemOrder;
    }

    public Boolean getIsProjectShared() {
        return isProjectShared;
    }

    public void setIsProjectShared(Boolean projectShared) {
        isProjectShared = projectShared;
    }

    public String getProjectShareOwnerName() {
        return projectShareOwnerName;
    }

    public void setProjectShareOwnerName(String projectShareOwnerName) {
        this.projectShareOwnerName = projectShareOwnerName;
    }

    public String getProjectShareOwnerEmail() {
        return projectShareOwnerEmail;
    }

    public void setProjectShareOwnerEmail(String projectShareOwnerEmail) {
        this.projectShareOwnerEmail = projectShareOwnerEmail;
    }

    public Boolean getIsShareApproved() {
        return isShareApproved;
    }

    public void setIsShareApproved(Boolean shareApproved) {
        isShareApproved = shareApproved;
    }

    public Boolean getIsOwnProject() {
        return isOwnProject;
    }

    public void setIsOwnProject(Boolean ownProject) {
        isOwnProject = ownProject;
    }

    public String getLastSyncedDateTime() {
        return lastSyncedDateTime;
    }

    public void setLastSyncedDateTime(String lastSyncedDateTime) {
        this.lastSyncedDateTime = lastSyncedDateTime;
    }

    public String getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(String lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getSyncClientCreationId() {
        return syncClientCreationId;
    }

    public void setSyncClientCreationId(String syncClientCreationId) {
        this.syncClientCreationId = syncClientCreationId;
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
