package com.arslan.invitationapp.invitationapp.viewmodel;

import java.time.LocalDateTime;

public class OrganizationViewModel {
    private int id;
    private String name;
    private boolean isActive;
    private boolean isDeleted;
    private LocalDateTime createdDatetime;
    private UserViewModel createdUser;
    private String code;

    public OrganizationViewModel() {
    }

    public OrganizationViewModel(int id, String name, boolean isActive, boolean isDeleted, LocalDateTime createdDate, UserViewModel createdUser, String code) {
        this.id = id;
        this.name = name;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
        this.createdDatetime = createdDate;
        this.createdUser = createdUser;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public LocalDateTime getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(LocalDateTime createdDate) {
        this.createdDatetime = createdDate;
    }

    public UserViewModel getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(UserViewModel createdUser) {
        this.createdUser = createdUser;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
