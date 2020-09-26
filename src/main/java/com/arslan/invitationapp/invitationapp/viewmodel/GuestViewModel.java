package com.arslan.invitationapp.invitationapp.viewmodel;

import java.time.LocalDate;

public class GuestViewModel {
    private long id;
    private long inviterId;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private boolean isCalled;
    private boolean isMailSent;
    private boolean willCome;
    private long organizationId;
    private boolean isActive;
    private boolean isDeleted;
    private LocalDate createdDatetime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getInviterId() {
        return inviterId;
    }

    public void setInviterId(long inviterId) {
        this.inviterId = inviterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

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

    public long getOrganizationId() {
        return organizationId;
    }

    private void setOrganizationId(long organizationId) {
        this.organizationId = organizationId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public LocalDate getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(LocalDate createdDatetime) {
        this.createdDatetime = createdDatetime;
    }
}
