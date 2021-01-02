package com.arslan.invitationapp.invitationapp.data.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Roles")
@Getter
@Setter
public class Role extends BaseEntity {
    @Column(name = "name")
    private String name;
}
