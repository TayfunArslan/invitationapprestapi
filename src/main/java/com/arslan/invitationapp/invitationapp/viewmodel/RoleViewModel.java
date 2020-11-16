package com.arslan.invitationapp.invitationapp.viewmodel;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RoleViewModel {
    private int id;
    private String name;
    private LocalDateTime createdDatetime;
    private boolean isActive;
    private boolean isDeleted;

    public RoleViewModel() {}

    public RoleViewModel(String name, LocalDateTime createdDate, boolean isActive, boolean isDeleted) {
        this.name = name;
        this.createdDatetime = createdDate;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
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

    public LocalDateTime getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(LocalDateTime createdDate) {
        this.createdDatetime = createdDate;
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
}
