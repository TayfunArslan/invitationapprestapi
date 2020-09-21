package com.arslan.invitationapp.invitationapp.viewmodel;

public class UserViewModel {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private int invitingUserId;
    private boolean isMailSent;
    private boolean isCalled;

    public UserViewModel() {
    }

    public UserViewModel(int id, String name, String surname, String email, String phone, int invitingUserId, boolean isMailSent, boolean isCalled) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.invitingUserId = invitingUserId;
        this.isMailSent = isMailSent;
        this.isCalled = isCalled;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getInvitingUserId() {
        return invitingUserId;
    }

    public void setInvitingUserId(int invitingUserId) {
        this.invitingUserId = invitingUserId;
    }

    public boolean isMailSent() {
        return isMailSent;
    }

    public void setMailSent(boolean mailSent) {
        isMailSent = mailSent;
    }

    public boolean isCalled() {
        return isCalled;
    }

    public void setCalled(boolean called) {
        isCalled = called;
    }
}
