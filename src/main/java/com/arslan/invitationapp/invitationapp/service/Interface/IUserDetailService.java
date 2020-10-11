package com.arslan.invitationapp.invitationapp.service.Interface;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserDetailService extends UserDetailsService {
    UserDetails loadUserByUsername(String username);
}
