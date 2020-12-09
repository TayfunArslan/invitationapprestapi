package com.arslan.invitationapp.invitationapp.viewmodel;

import org.springframework.security.core.userdetails.UserDetails;

public interface CustomUserDetails extends UserDetails {
    long getUserId();
}
