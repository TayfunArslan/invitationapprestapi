package com.arslan.invitationapp.invitationapp.service;

import com.arslan.invitationapp.invitationapp.data.entity.UserRole;
import com.arslan.invitationapp.invitationapp.data.repository.IUserRepository;
import com.arslan.invitationapp.invitationapp.data.repository.IUserRoleRepository;
import com.arslan.invitationapp.invitationapp.enums.ResponseStatus;
import com.arslan.invitationapp.invitationapp.service.Interface.IUserDetailService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements IUserDetailService {
    private final IUserRepository m_userRepository;
    private final IUserRoleRepository m_userRoleRepository;

    public UserDetailService(IUserRepository userRepository,
                             IUserRoleRepository userRoleRepository) {
        m_userRepository = userRepository;
        m_userRoleRepository = userRoleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        var user = m_userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        var userRole = m_userRoleRepository.findByUserId(user.getId())
                .orElse(new UserRole());

        return UserDetailsImpl.build(user, userRole);
    }
}
