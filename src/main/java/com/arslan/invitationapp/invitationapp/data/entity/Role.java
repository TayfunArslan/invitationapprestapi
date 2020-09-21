package com.arslan.invitationapp.invitationapp.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Roles")
public class Role extends BaseEntity {
    @Column(name = "name")
    private int name;

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }
}
