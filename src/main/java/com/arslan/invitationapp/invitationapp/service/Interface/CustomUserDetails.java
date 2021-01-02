package com.arslan.invitationapp.invitationapp.service.Interface;

import org.springframework.security.core.userdetails.UserDetails;

public interface CustomUserDetails extends UserDetails {
    long getUserId();
}
