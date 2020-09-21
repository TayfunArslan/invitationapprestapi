package com.arslan.invitationapp.invitationapp.viewmodel;

public class GuestViewModel {
    private int id;
    private int inviterId;
    private String name;
    private String phone;
    private String email;
    private boolean isCalled;
    private boolean isMailSent;
    private boolean willCome;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInviterId() {
        return inviterId;
    }

    public void setInviterId(int inviterId) {
        this.inviterId = inviterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
