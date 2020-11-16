package com.arslan.invitationapp.invitationapp.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "User_Roles")
public class UserRole extends BaseEntity{
    private int userId;
    private int roleId;

    @OneToOne
    @Transient
    private User user;

    @OneToOne
    @Transient
    private Role role;

    @OneToOne
    private Organization organization;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
