package com.arslan.invitationapp.invitationapp.viewmodel;

import java.time.LocalDateTime;

public class OrganizationViewModel {
    private int id;
    private String name;
    private String code;
    private long createdUserId;
    private boolean isActive;
    private boolean isDeleted;
    private LocalDateTime createdDatetime;

    public OrganizationViewModel() {}

    public OrganizationViewModel(int id, String name, String code, long createdUserId, boolean isActive,
                                 boolean isDeleted, LocalDateTime createdDatetime) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.createdUserId = createdUserId;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
        this.createdDatetime = createdDatetime;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getCreatedUserId() {
        return createdUserId;
    }

    public void setCreatedUserId(long createdUserId) {
        this.createdUserId = createdUserId;
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

    public void setCreatedDatetime(LocalDateTime createdDatetime) {
        this.createdDatetime = createdDatetime;
    }
}
