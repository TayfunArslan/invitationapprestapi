package com.arslan.invitationapp.invitationapp.service;

import com.arslan.invitationapp.invitationapp.data.entity.OrganizationCoOwner;
import com.arslan.invitationapp.invitationapp.data.repository.*;
import com.arslan.invitationapp.invitationapp.enums.ResponseStatus;
import com.arslan.invitationapp.invitationapp.enums.Role;
import com.arslan.invitationapp.invitationapp.mapper.IMapper;
import com.arslan.invitationapp.invitationapp.service.Interface.IUserService;
import com.arslan.invitationapp.invitationapp.service.Interface.CustomUserDetails;
import com.arslan.invitationapp.invitationapp.viewmodel.serviceResult.ErrorModel;
import com.arslan.invitationapp.invitationapp.viewmodel.serviceResult.ServiceResult;
import com.arslan.invitationapp.invitationapp.viewmodel.UserViewModel;
import com.arslan.invitationapp.invitationapp.enums.ErrorCodes;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;

@Service
@Transactional(rollbackOn = Exception.class)
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
        //13 Kasım update: Organizasyon yaratırken rol verilsin.
        var serviceResult = new ServiceResult<UserViewModel>();
        Optional<Integer> errorCode = empty();

        try {
            var isUsernameExist = m_userRepository.existsByUsername(userViewModel.getUsername());
            if (isUsernameExist) {
                errorCode = Optional.of(ErrorCodes.USERNAME_EXIST.getCode());
                throw new Exception(ErrorCodes.USERNAME_EXIST.name());
            }

            var isEmailExist = m_userRepository.existsByEmail(userViewModel.getEmail());
            if (isEmailExist) {
                errorCode = Optional.of(ErrorCodes.MAIL_EXIST.getCode());
                throw new Exception("User already saved");
            }

            userViewModel.setPassword(m_passwordEncoder.encode(userViewModel.getPassword()));
            userViewModel.setActive(true);
            userViewModel.setDeleted(false);
            userViewModel.setCreatedDatetime(LocalDateTime.now());

            var user = m_userRepository.save(m_mapper.userViewModelToUser(userViewModel));

            serviceResult.setData(m_mapper.userToUserViewModel(user));
            serviceResult.setResponseStatus(ResponseStatus.OK);
        } catch (Throwable ex) {
            if (errorCode.isEmpty())
                errorCode = Optional.of(ErrorCodes.UNKNOWN_ERROR.getCode());

            serviceResult.setData(new UserViewModel());
            serviceResult.setResponseStatus(ResponseStatus.FAIL);
            serviceResult.setErrorModel(new ErrorModel(errorCode, "Exception@AddUser " + ex.getMessage()));
        }

        return serviceResult;
    }

    @Override
    public ServiceResult<UserViewModel> inviteUserToOrganization(long userId, long organizationId) {
        //Todo davet mekanizması nasıl olacak? userId'yi bilemeyebilirim. Mail yada telefon numarası ile
        // değiştirilebilir.
        var serviceResult = new ServiceResult<UserViewModel>();
        Optional<Integer> errorCode = Optional.empty();

        try {
            var user = m_userRepository.findById(userId);
            var organization = m_organizationRepository.findById(organizationId);


            if (user.isEmpty()) {
                errorCode = Optional.of(ErrorCodes.USER_NOT_FOUND.getCode());
                throw new Exception("User not found");
            }

            if (organization.isEmpty()) {
                errorCode = Optional.of(ErrorCodes.ORGANIZATION_NOT_FOUND.getCode());
                throw new Exception("Organization not found");
            }

            //OrganizationCoOwner'a mod rolü ile kayıt atılmalı. TODO İleride değiştirilebilir. Role de dışarıdan alınabilir.
            var role = m_roleRepository.findByName(Role.MOD.name());

            if (role.isEmpty()) {
                var modRole = new com.arslan.invitationapp.invitationapp.data.entity.Role();
                modRole.setActive(true);
                modRole.setDeleted(false);
                modRole.setName(Role.MOD.name());
                modRole.setCreatedDatetime(LocalDateTime.now());

                role = Optional.of(modRole);
            }

            var organizationCoOwner = new OrganizationCoOwner();
            organizationCoOwner.setCoOwner(user.get());
            organizationCoOwner.setRole(role.get());
            organizationCoOwner.setOrganization(organization.get());
            organizationCoOwner.setActive(true);
            organizationCoOwner.setDeleted(false);
            organizationCoOwner.setCreatedDatetime(LocalDateTime.now());

            m_organizationCoOwnerRepository.save(organizationCoOwner);
        } catch (Throwable ex) {
            if (errorCode.isEmpty())
                errorCode = Optional.of(ErrorCodes.UNKNOWN_ERROR.getCode());

            serviceResult.setResponseStatus(ResponseStatus.FAIL);
            serviceResult.setErrorModel(new ErrorModel(errorCode, "Exception@InviteUserToOrganization" + ex.getMessage()));
        }

        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> removeUser(long userId) {
        var serviceResult = new ServiceResult<Boolean>();
        Optional<Integer> errorCode = Optional.empty();

        try {
            var user = m_userRepository.findById(userId);

            if (user.isEmpty()) {
                errorCode = Optional.of(ErrorCodes.USER_NOT_FOUND.getCode());
                throw new Exception("User not found");
            }

            user.get().setDeleted(true);
            user.get().setActive(false);

            m_userRepository.save(user.get());

            serviceResult.setData(true);
            serviceResult.setResponseStatus(ResponseStatus.OK);
        } catch (Throwable ex) {
            if (errorCode.isEmpty())
                errorCode = Optional.of(ErrorCodes.UNKNOWN_ERROR.getCode());

            serviceResult.setResponseStatus(ResponseStatus.FAIL);
            serviceResult.setErrorModel(new ErrorModel(errorCode, "Exception@RemoveUser" + ex.getMessage()));
        }

        return serviceResult;
    }

    @Override
    public ServiceResult<List<UserViewModel>> getAllUserByOrganizationId(long organizationId) {
        var serviceResult = new ServiceResult<List<UserViewModel>>();

        try {
            //OrganizationOwner and CoOwners
//            var userList = new ArrayList<UserViewModel>();
//
//            var owner = m_organizationRepository.findUserById(organizationId);
//            owner.ifPresent(o -> {
//                var userViewModel = m_mapper.userToUserViewModel(o);
//                userList.add(userViewModel);
//            });
//
//            var coOwners = m_organizationCoOwnerRepository
//                    .findUsersById(organizationId)
//                    .stream()
//                    .map(m_mapper::userToUserViewModel)
//                    .collect(Collectors.toList());
//
//            userList.addAll(coOwners);
//
//            serviceResult.setData(userList);
            serviceResult.setResponseStatus(ResponseStatus.OK);
        } catch (Throwable ex) {
            serviceResult.setResponseStatus(ResponseStatus.FAIL);
        }

        return serviceResult;
    }

    @Override
    public ServiceResult<UserViewModel> getUserByUsername(String username) {
        var serviceResult = new ServiceResult<UserViewModel>();
        Optional<Integer> errorCode = Optional.empty();

        try {
            var user = m_userRepository.findByUsername(username);

            if (user.isEmpty()) {
                errorCode = Optional.of(ErrorCodes.USER_NOT_FOUND.getCode());
                throw new Exception("User not found");
            }

            serviceResult.setData(m_mapper.userToUserViewModel(user.get()));
            serviceResult.setResponseStatus(ResponseStatus.OK);
        } catch (Throwable ex) {
            if (errorCode.isEmpty())
                errorCode = Optional.of(ErrorCodes.UNKNOWN_ERROR.getCode());

            serviceResult.setResponseStatus(ResponseStatus.FAIL);
            serviceResult.setErrorModel(new ErrorModel(errorCode, "Exception@GetUserByUsername" + ex.getMessage()));
        }

        return serviceResult;
    }

    @Override
    public ServiceResult<UserViewModel> login(String username, String password) {
        var serviceResult = new ServiceResult<UserViewModel>();
        Optional<Integer> errorCode = Optional.empty();

        try {
            var user = m_userRepository.findByUsername(username);

            if (user.isEmpty()) {
                errorCode = Optional.of(ErrorCodes.USER_NOT_FOUND.getCode());
                throw new Exception("User not found");
            }

            if (!m_passwordEncoder.matches(password, user.get().getPassword())) {
                errorCode = Optional.of(ErrorCodes.WRONG_PASSWORD.getCode());
                throw new Exception("Password is incorrect");
            }

            serviceResult.setData(m_mapper.userToUserViewModel(user.get()));
            serviceResult.setResponseStatus(ResponseStatus.OK);
        } catch (Throwable ex) {
            if (errorCode.isEmpty())
                errorCode = Optional.of(ErrorCodes.UNKNOWN_ERROR.getCode());

            serviceResult.setResponseStatus(ResponseStatus.FAIL);
            serviceResult.setErrorModel(new ErrorModel(errorCode, "Exception@login" + ex.getMessage()));
        }

        return serviceResult;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username) {
        var user = m_userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new CustomUserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public String getPassword() {
                return user.getPassword();
            }

            @Override
            public long getUserId() {
                return user.getId();
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
