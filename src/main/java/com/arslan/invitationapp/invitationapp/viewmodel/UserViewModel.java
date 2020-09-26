package com.arslan.invitationapp.invitationapp.viewmodel;

import java.time.LocalDate;

public class UserViewModel {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private LocalDate createdDatetime;
    private boolean isActive;
    private boolean isDeleted;

    public UserViewModel() {
    }

    public UserViewModel(int id, String name, String surname, String email, String phone, LocalDate createdDatetime, boolean isActive, boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.createdDatetime = createdDatetime;
        this.isActive = isActive;
        this.isDeleted = isDeleted;
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

    public LocalDate getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(LocalDate createdDatetime) {
        this.createdDatetime = createdDatetime;
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
}
