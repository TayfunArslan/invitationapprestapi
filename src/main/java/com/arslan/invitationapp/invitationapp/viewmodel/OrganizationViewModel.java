package com.arslan.invitationapp.invitationapp.viewmodel;

import com.arslan.invitationapp.invitationapp.data.entity.User;

public class OrganizationViewModel {
    private int id;
    private String name;
    private User createdUser;

    public OrganizationViewModel() {}

    public OrganizationViewModel(int id, String name, User createdUser) {
        this.id = id;
        this.name = name;
        this.createdUser = createdUser;
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

    public User getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(int createdUserId) {
        this.createdUser = createdUser;
    }
}
