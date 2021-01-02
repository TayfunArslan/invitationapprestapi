package com.arslan.invitationapp.invitationapp.service;

import com.arslan.invitationapp.invitationapp.data.entity.Role;
import com.arslan.invitationapp.invitationapp.data.repository.IRoleRepository;
import com.arslan.invitationapp.invitationapp.service.Interface.IRoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements IRoleService {
    private final IRoleRepository m_roleRepository;

    public RoleService(IRoleRepository roleRepository) {
        m_roleRepository = roleRepository;
    }
}
