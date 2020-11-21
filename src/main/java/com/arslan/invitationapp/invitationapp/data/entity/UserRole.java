package com.arslan.invitationapp.invitationapp.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "User_Roles")
public class UserRole extends BaseEntity{
    @OneToOne
    private User user;

    @OneToOne
    private Role role;

    @OneToOne
    private Organization organization;

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
