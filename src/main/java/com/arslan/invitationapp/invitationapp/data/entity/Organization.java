package com.arslan.invitationapp.invitationapp.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "Organizations")
public class Organization extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @OneToOne
    @JoinColumn(name = "created_user_id")
    private User createdUser;

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

    public User getCreatedUserId() {
        return createdUser;
    }

    public void setCreatedUser(User createdUser) {
        this.createdUser = createdUser;
    }
}
