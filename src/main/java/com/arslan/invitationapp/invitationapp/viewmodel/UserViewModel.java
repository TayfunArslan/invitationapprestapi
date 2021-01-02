package com.arslan.invitationapp.invitationapp.viewmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserViewModel {
    private long id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private String email;
    private String phone;
    private LocalDateTime createdDatetime;
    private boolean isActive;
    private boolean isDeleted;
}
