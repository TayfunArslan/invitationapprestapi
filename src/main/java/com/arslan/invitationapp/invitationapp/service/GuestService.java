package com.arslan.invitationapp.invitationapp.service;

import com.arslan.invitationapp.invitationapp.data.repository.IGuestRepository;
import com.arslan.invitationapp.invitationapp.data.repository.IUserRepository;
import org.springframework.stereotype.Service;

@Service
public class GuestService {
    private IGuestRepository m_guestRepository;
    private IUserRepository m_userRepository;

    public GuestService(IGuestRepository guestRepository, IUserRepository userRepository) {
        m_guestRepository = guestRepository;
        m_userRepository = userRepository;
    }
}
