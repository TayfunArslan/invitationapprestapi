package com.arslan.invitationapp.invitationapp.viewmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    private LocalDateTime createdDatetime;
}
