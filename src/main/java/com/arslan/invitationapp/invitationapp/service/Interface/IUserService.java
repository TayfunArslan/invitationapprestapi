package com.arslan.invitationapp.invitationapp.service.Interface;

import com.arslan.invitationapp.invitationapp.service.ServiceResult;
import com.arslan.invitationapp.invitationapp.viewmodel.UserViewModel;

public interface IUserService {
    public ServiceResult<UserViewModel> AddUser(UserViewModel coOwner);
    public ServiceResult<Boolean> RemoveUser(long userId);
}
