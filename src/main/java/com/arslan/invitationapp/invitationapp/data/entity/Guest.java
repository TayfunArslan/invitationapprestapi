package com.arslan.invitationapp.invitationapp.data.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Guests")
public class Guest extends BaseEntity{
    @Column(name = "is_called")
    private boolean isCalled;

    @Column(name = "is_mail_sent")
    private boolean isMailSent;

    @Column(name = "will_come")
    private boolean willCome;

    @OneToOne
    private Organization organization;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "inviter_id", nullable = false)
    private User user;

    public boolean isCalled() {
        return isCalled;
    }

    public void setCalled(boolean called) {
        isCalled = called;
    }

    public boolean isMailSent() {
        return isMailSent;
    }

    public void setMailSent(boolean mailSent) {
        isMailSent = mailSent;
    }

    public boolean isWillCome() {
        return willCome;
    }

    public void setWillCome(boolean willCome) {
        this.willCome = willCome;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
