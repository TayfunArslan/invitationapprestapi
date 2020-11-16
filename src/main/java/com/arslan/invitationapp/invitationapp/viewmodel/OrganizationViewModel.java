package com.arslan.invitationapp.invitationapp.viewmodel;

import com.arslan.invitationapp.invitationapp.data.entity.User;

public class OrganizationViewModel {
    private int id;
    private String name;
    private int createdUserId;

    public OrganizationViewModel() {}

    public OrganizationViewModel(int id, String name, int createdUserId) {
        this.id = id;
        this.name = name;
        this.createdUserId = createdUserId;
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

    public int getCreatedUserId() {
        return createdUserId;
    }

    public void setCreatedUserId(int createdUserId) {
        this.createdUserId = createdUserId;
    }
}
