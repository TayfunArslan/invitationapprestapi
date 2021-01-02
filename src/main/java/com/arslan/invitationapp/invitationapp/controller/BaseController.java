package com.arslan.invitationapp.invitationapp.controller;


import com.arslan.invitationapp.invitationapp.service.Interface.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

public class BaseController {
    public long getCurrentUserId() {
        var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return ((CustomUserDetails) principal).getUserId();
    }
}
