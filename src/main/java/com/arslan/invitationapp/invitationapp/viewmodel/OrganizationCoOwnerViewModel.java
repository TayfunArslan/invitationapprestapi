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
public class OrganizationCoOwnerViewModel {
    private long id;
    private LocalDateTime createdDate;
    private boolean isActive;
    private boolean isDeleted;
    private long organizationId;
    private long coOwnerId;
    private long roleId;
}
