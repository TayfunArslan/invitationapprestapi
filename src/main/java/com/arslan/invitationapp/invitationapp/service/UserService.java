package com.arslan.invitationapp.invitationapp.service;

import com.arslan.invitationapp.invitationapp.data.entity.OrganizationCoOwner;
import com.arslan.invitationapp.invitationapp.data.repository.*;
import com.arslan.invitationapp.invitationapp.enums.ResponseStatus;
import com.arslan.invitationapp.invitationapp.mapper.IMapper;
import com.arslan.invitationapp.invitationapp.service.Interface.IUserService;
import com.arslan.invitationapp.invitationapp.viewmodel.OrganizationCoOwnerViewModel;
import com.arslan.invitationapp.invitationapp.viewmodel.UserViewModel;
import org.postgresql.shaded.com.ongres.scram.common.util.CryptoUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.CryptoPrimitive;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {
    private final IUserRepository m_userRepository;
    private final IOrganizationRepository m_organizationRepository;
    private final IOrganizationCoOwnerRepository m_organizationCoOwnerRepository;
    private final IUserRoleRepository m_userRoleRepository;
    private final IRoleRepository m_roleRepository;
    private final IMapper m_mapper;
    private final PasswordEncoder m_passwordEncoder;

    public UserService(IUserRepository userRepository,
                       IOrganizationRepository organizationRepository,
                       IOrganizationCoOwnerRepository organizationCoOwnerRepository,
                       IUserRoleRepository userRoleRepository,
                       IRoleRepository roleRepository,
                       IMapper mapper,
                       PasswordEncoder passwordEncoder) {
        m_userRepository = userRepository;
        m_organizationRepository = organizationRepository;
        m_organizationCoOwnerRepository = organizationCoOwnerRepository;
        m_userRoleRepository = userRoleRepository;
        m_roleRepository = roleRepository;
        m_mapper = mapper;
        m_passwordEncoder = passwordEncoder;
    }

    @Override
    public ServiceResult<UserViewModel> addUser(UserViewModel userViewModel) {
        //Signup için kullanılan metot. Kullanıcı hangi yetkide olacak ? UserViewModel içinde verilebilir.
        var serviceResult = new ServiceResult<UserViewModel>();

        try {
            var isUsernameExist = m_userRepository.existsByUsername(userViewModel.getUsername());
            var isEmailExist = m_userRepository.existsByUsername(userViewModel.getUsername());

            if(isEmailExist || isUsernameExist)
                throw new Exception("User already saved");

            userViewModel.setPassword(m_passwordEncoder.encode(userViewModel.getPassword()));
            userViewModel.setActive(true);
            userViewModel.setDeleted(false);
            userViewModel.setCreatedDatetime(LocalDate.now());

            var user = m_userRepository.save(m_mapper.userViewModelToUser(userViewModel));

            serviceResult.setData(m_mapper.userToUserViewModel(user));
            serviceResult.setResponseStatus(ResponseStatus.OK);
        } catch (Throwable ex) {
            serviceResult.setData(new UserViewModel());
            serviceResult.setResponseStatus(ResponseStatus.FAIL);
            serviceResult.setMessage("Exception@setCoOwner" + ex.getMessage());
        }

        return serviceResult;
    }

    @Override
    public ServiceResult<UserViewModel> inviteUserToOrganization(long userId, long organizationId) {
        //Todo davet mekanizması nasıl olacak? userId'yi bilemeyebilirim. Mail yada telefon numarası ile
        // değiştirilebilir.
        var serviceResult = new ServiceResult<UserViewModel>();

        try {
            var user = m_userRepository.findById(userId);
            var organization = m_organizationRepository.findById(organizationId);

            if(user.isEmpty())
                throw new Exception("User not found");

            if(organization.isEmpty())
                throw new Exception("Organization not found");

            //OrganizationCoOwner'a mod rolü ile kayıt atılmalı. TODO İleride değiştirilebilir. Role de dışarıdan alınabilir.
            var role = m_roleRepository.findByName("mod");
            if(role.isEmpty())
                throw new Exception("Role not found");

            var organizationCoOwner = new OrganizationCoOwner();
            organizationCoOwner.setCoOwner(user.get());
            organizationCoOwner.setRole(role.get());
            organizationCoOwner.setOrganization(organization.get());
            organizationCoOwner.setActive(true);
            organizationCoOwner.setDeleted(false);
            organizationCoOwner.setCreatedDatetime(LocalDate.now());

            m_organizationCoOwnerRepository.save(organizationCoOwner);
        } catch (Throwable ex) {
            serviceResult.setMessage(ex.getMessage());
            serviceResult.setResponseStatus(ResponseStatus.FAIL);
        }

        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> removeUser(long userId) {
        var serviceResult = new ServiceResult<Boolean>();

        try {
            var user = m_userRepository.findById(userId);

            if (user.isEmpty())
                throw new Exception("User not found");

            user.get().setDeleted(true);
            user.get().setActive(false);

            m_userRepository.save(user.get());

            serviceResult.setData(true);
            serviceResult.setResponseStatus(ResponseStatus.OK);
            serviceResult.setMessage("");
        } catch (Throwable ex) {
            serviceResult.setData(false);
            serviceResult.setMessage(ex.getMessage());
            serviceResult.setResponseStatus(ResponseStatus.FAIL);
        }

        return serviceResult;
    }

    @Override
    public ServiceResult<List<UserViewModel>> getAllUserByOrganizationId(long organizationId) {
        var serviceResult = new ServiceResult<List<UserViewModel>>();

        try {
            //OrganizationOwner and CoOwners
            var userList = new ArrayList<UserViewModel>();

            var owner = m_organizationRepository.findUserById(organizationId);
            owner.ifPresent(o -> {
                var userViewModel = m_mapper.userToUserViewModel(o);
                userList.add(userViewModel);
            });

            var coOwners = m_organizationCoOwnerRepository
                    .findUsersById(organizationId)
                    .stream()
                    .map(m_mapper::userToUserViewModel)
                    .collect(Collectors.toList());

            userList.addAll(coOwners);

            serviceResult.setData(userList);
            serviceResult.setResponseStatus(ResponseStatus.OK);
        } catch (Throwable ex) {
            serviceResult.setResponseStatus(ResponseStatus.FAIL);
            serviceResult.setMessage(ex.getMessage());
        }

        return serviceResult;
    }

    @Override
    public ServiceResult<UserViewModel> getUserByUsername(String username) {
        var serviceResult = new ServiceResult<UserViewModel>();

        try {
            var user = m_userRepository.findByUsername(username);

            if(user.isEmpty())
                throw new Exception("User not found");

            serviceResult.setData(m_mapper.userToUserViewModel(user.get()));
            serviceResult.setResponseStatus(ResponseStatus.OK);
        } catch (Throwable ex) {
            serviceResult.setResponseStatus(ResponseStatus.FAIL);
            serviceResult.setMessage(ex.getMessage());
            serviceResult.setData(new UserViewModel());
        }

        return serviceResult;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        var user = m_userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public String getPassword() {
                return user.getPassword();
            }

            @Override
            public String getUsername() {
                return user.getUsername();
            }

            @Override
            public boolean isAccountNonExpired() {
                return false;
            }

            @Override
            public boolean isAccountNonLocked() {
                return !user.isDeleted();
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return false;
            }

            @Override
            public boolean isEnabled() {
                return user.isActive();
            }
        };
    }
}
