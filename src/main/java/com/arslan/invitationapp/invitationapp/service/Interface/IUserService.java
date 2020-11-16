package com.arslan.invitationapp.invitationapp.service.Interface;

import com.arslan.invitationapp.invitationapp.service.ServiceResult;
import com.arslan.invitationapp.invitationapp.viewmodel.UserViewModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    ServiceResult<UserViewModel> addUser(UserViewModel coOwner);
    ServiceResult<UserViewModel> inviteUserToOrganization(long userId, long organizationId);
    ServiceResult<Boolean> removeUser(long userId);
    ServiceResult<List<UserViewModel>> getAllUserByOrganizationId(long organizationId);
    ServiceResult<UserViewModel> getUserByUsername(String username);
    ServiceResult<UserViewModel> login(String username, String password);
}
