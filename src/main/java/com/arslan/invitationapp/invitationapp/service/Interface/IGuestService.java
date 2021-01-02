package com.arslan.invitationapp.invitationapp.service.Interface;

import com.arslan.invitationapp.invitationapp.viewmodel.serviceResult.ServiceResult;
import com.arslan.invitationapp.invitationapp.viewmodel.GuestViewModel;

import java.util.List;

public interface IGuestService {
    ServiceResult<List<GuestViewModel>> getAllByOrganizationId(long organizationId);

    ServiceResult<GuestViewModel> addGuest(GuestViewModel guestViewModel, long currentUserId);

    ServiceResult<Boolean> removeGuest(long guestId);
}
