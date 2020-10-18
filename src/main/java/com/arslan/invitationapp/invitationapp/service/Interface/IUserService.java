package com.arslan.invitationapp.invitationapp.service.Interface;

import com.arslan.invitationapp.invitationapp.service.ServiceResult;
import com.arslan.invitationapp.invitationapp.viewmodel.UserViewModel;

import java.util.List;

public interface IUserService {
    ServiceResult<UserViewModel> addUser(UserViewModel coOwner);
    ServiceResult<UserViewModel> inviteUserToOrganization(long userId, long organizationId);
    ServiceResult<Boolean> removeUser(long userId);
    ServiceResult<List<UserViewModel>> getAllUserByOrganizationId(long organizationId);
    ServiceResult<UserViewModel> getUserByUsername(String username);
}
