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
public class OrganizationViewModel {
    private long id;
    private String name;
    private String code;
    private long createdUserId;
    private boolean isActive;
    private boolean isDeleted;
    private LocalDateTime createdDatetime;
}
