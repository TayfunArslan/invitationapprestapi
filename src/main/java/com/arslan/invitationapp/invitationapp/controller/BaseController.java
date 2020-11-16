package com.arslan.invitationapp.invitationapp.controller;


import com.arslan.invitationapp.invitationapp.data.repository.IUserRepository;
import com.arslan.invitationapp.invitationapp.enums.ResponseStatus;
import com.arslan.invitationapp.invitationapp.service.Interface.IUserService;
import com.sun.security.auth.UserPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class BaseController {
    private final IUserService m_userService;

    public BaseController(IUserService userService) {
        m_userService = userService;
    }

    public long getCurrentUserId() {
        var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var username = ((UserDetails) principal).getUsername();

        var user = m_userService.getUserByUsername(username);

        if(user.getResponseStatus() == ResponseStatus.FAIL)
            return 0;

        return user.getData().getId();
    }
}
