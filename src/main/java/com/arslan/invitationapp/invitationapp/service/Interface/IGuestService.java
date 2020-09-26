package com.arslan.invitationapp.invitationapp.service.Interface;

import com.arslan.invitationapp.invitationapp.service.ServiceResult;
import com.arslan.invitationapp.invitationapp.viewmodel.GuestViewModel;

public interface IGuestService {
    public ServiceResult<GuestViewModel> addGuest(GuestViewModel guestViewModel);
    public ServiceResult<Boolean> removeGuest(long guestId);
}
