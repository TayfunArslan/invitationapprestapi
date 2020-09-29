package com.arslan.invitationapp.invitationapp.service.Interface;

import com.arslan.invitationapp.invitationapp.service.ServiceResult;
import com.arslan.invitationapp.invitationapp.viewmodel.UserViewModel;
import java.util.List;

public interface IUserService {
    public ServiceResult<UserViewModel> addUser(UserViewModel coOwner);
    public ServiceResult<Boolean> removeUser(long userId);
    public ServiceResult<List<UserViewModel>> getAllUserByOrganizationId(long organizationId);
}
