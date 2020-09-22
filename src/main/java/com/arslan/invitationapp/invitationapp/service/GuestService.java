package com.arslan.invitationapp.invitationapp.service;

import com.arslan.invitationapp.invitationapp.data.repository.IGuestRepository;
import com.arslan.invitationapp.invitationapp.data.repository.IUserRepository;
import com.arslan.invitationapp.invitationapp.mapper.IMapper;
import org.springframework.stereotype.Service;

@Service
public class GuestService  {
    private IGuestRepository m_guestRepository;
    private IUserRepository m_userRepository;
    private IMapper m_mapper;

    public GuestService(IGuestRepository guestRepository, IUserRepository userRepository, IMapper mapper) {
        m_guestRepository = guestRepository;
        m_userRepository = userRepository;
        m_mapper = mapper;
    }
}
