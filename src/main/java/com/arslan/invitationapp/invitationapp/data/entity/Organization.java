package com.arslan.invitationapp.invitationapp.data.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Organizations")
public class Organization extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Guest> guests;

    @ManyToOne
    @JoinColumn(name = "created_user_id") //We don't have to write. Hibernate writes automatically
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

    public List<Guest> getGuests() {
        return guests;
    }

    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }

    public User getCreatedUser() {
        return createdUser;
    }

    public User getCreatedUserId() {
        return createdUser;
    }

    public void setCreatedUser(User createdUser) {
        this.createdUser = createdUser;
    }
}
