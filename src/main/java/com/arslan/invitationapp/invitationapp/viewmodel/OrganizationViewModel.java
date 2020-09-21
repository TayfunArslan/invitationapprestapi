package com.arslan.invitationapp.invitationapp.viewmodel;

public class OrganizationViewModel {
    private int id;
    private String name;
    private String createdUserId;

    public OrganizationViewModel() {}

    public OrganizationViewModel(int id, String name, String createdUserId) {
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

    public String getCreatedUserId() {
        return createdUserId;
    }

    public void setCreatedUserId(String createdUserId) {
        this.createdUserId = createdUserId;
    }
}
