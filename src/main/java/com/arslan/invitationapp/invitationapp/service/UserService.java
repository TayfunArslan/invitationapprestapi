package com.arslan.invitationapp.invitationapp.service;

import com.arslan.invitationapp.invitationapp.data.entity.User;
import com.arslan.invitationapp.invitationapp.data.repository.IOrganizationCoOwnerRepository;
import com.arslan.invitationapp.invitationapp.data.repository.IOrganizationRepository;
import com.arslan.invitationapp.invitationapp.data.repository.IUserRepository;
import com.arslan.invitationapp.invitationapp.enums.ResponseStatus;
import com.arslan.invitationapp.invitationapp.mapper.IMapper;
import com.arslan.invitationapp.invitationapp.service.Interface.IUserService;
import com.arslan.invitationapp.invitationapp.viewmodel.UserViewModel;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {
    private final IUserRepository m_userRepository;
    private final IOrganizationRepository m_organizationRepository;
    private final IOrganizationCoOwnerRepository m_organizationCoOwnerRepository;
    private final IMapper m_mapper;

    public UserService(IUserRepository userRepository,
                       IOrganizationRepository organizationRepository,
                       IOrganizationCoOwnerRepository organizationCoOwnerRepository,
                       IMapper mapper) {
        m_userRepository = userRepository;
        m_organizationRepository = organizationRepository;
        m_organizationCoOwnerRepository = organizationCoOwnerRepository;
        m_mapper = mapper;
    }

    @Override
    public ServiceResult<UserViewModel> addUser(UserViewModel userViewModel) {
        var serviceResult = new ServiceResult<UserViewModel>();

        try {
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
    public ServiceResult<Boolean> removeUser(long userId) {
        var serviceResult = new ServiceResult<Boolean>();

        try {
            var user = m_userRepository.findById(userId);

            if (user.isEmpty())
                throw new Exception("User not found");

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
}
