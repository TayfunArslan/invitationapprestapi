package com.arslan.invitationapp.invitationapp.viewmodel.request;

import java.time.LocalDate;

public class SignupRequestModel {
    private int id;
    private String name;
    private String surname;
    private String username;
    private String email;
    private String phone;
    private LocalDate createdDatetime;
    private boolean isActive;
    private boolean isDeleted;
}
