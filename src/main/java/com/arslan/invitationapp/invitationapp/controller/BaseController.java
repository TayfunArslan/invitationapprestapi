package com.arslan.invitationapp.invitationapp.controller;


import com.arslan.invitationapp.invitationapp.data.repository.IUserRepository;
import com.arslan.invitationapp.invitationapp.enums.ResponseStatus;
import com.arslan.invitationapp.invitationapp.service.Interface.IUserService;
import com.arslan.invitationapp.invitationapp.viewmodel.CustomUserDetails;
import com.sun.security.auth.UserPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class BaseController {
    public long getCurrentUserId() {
        var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return ((CustomUserDetails) principal).getUserId();
    }
}
