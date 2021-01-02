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
public class RoleViewModel {
    private long id;
    private String name;
    private LocalDateTime createdDatetime;
    private boolean isActive;
    private boolean isDeleted;
}
