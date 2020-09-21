package com.arslan.invitationapp.invitationapp.service;

import com.arslan.invitationapp.invitationapp.data.repository.IUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private IUserRepository m_userRepository;

    public UserService(IUserRepository userRepository) {
        m_userRepository = userRepository;
    }
}
