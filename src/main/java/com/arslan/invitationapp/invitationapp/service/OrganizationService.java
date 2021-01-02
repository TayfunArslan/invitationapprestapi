package com.arslan.invitationapp.invitationapp.service;

import com.arslan.invitationapp.invitationapp.data.entity.Role;
import com.arslan.invitationapp.invitationapp.data.entity.UserRole;
import com.arslan.invitationapp.invitationapp.data.repository.*;
import com.arslan.invitationapp.invitationapp.enums.ResponseStatus;
import com.arslan.invitationapp.invitationapp.mapper.IMapper;
import com.arslan.invitationapp.invitationapp.service.Interface.IOrganizationService;
import com.arslan.invitationapp.invitationapp.viewmodel.OrganizationViewModel;
import com.arslan.invitationapp.invitationapp.enums.ErrorCodes;
import com.arslan.invitationapp.invitationapp.viewmodel.serviceResult.ErrorModel;
import com.arslan.invitationapp.invitationapp.viewmodel.serviceResult.ServiceResult;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrganizationService implements IOrganizationService {
    private final IOrganizationRepository m_organizationRepository;
    private final IUserRepository m_userRepository;
    private final IRoleRepository m_roleRepository;
    private final IUserRoleRepository m_userRoleRepository;
    private final IMapper m_mapper;

    public OrganizationService(IOrganizationRepository organizationRepository,
                               IUserRepository userRepository,
                               IRoleRepository roleRepository,
                               IUserRoleRepository userRoleRepository,
                               IMapper mapper) {
        m_organizationRepository = organizationRepository;
        m_userRepository = userRepository;
        m_roleRepository = roleRepository;
        m_userRoleRepository = userRoleRepository;
        m_mapper = mapper;
    }

    public ServiceResult<List<OrganizationViewModel>> getMyOrganizations(long currentUserId) {
        var serviceResult = new ServiceResult<List<OrganizationViewModel>>();
        Optional<Integer> errorCode;

        try {
            var organizations =
                    new ArrayList<>(m_organizationRepository
                            .findOrganizationsByCreatedUserId(currentUserId));

            serviceResult.setData(organizations.stream().map(m_mapper::organizationToOrganizationViewModel).collect(Collectors.toList()));
            serviceResult.setResponseStatus(ResponseStatus.OK);
        } catch (Throwable ex) {
            errorCode = Optional.of(ErrorCodes.UNKNOWN_ERROR.getCode());

            serviceResult.setResponseStatus(ResponseStatus.FAIL);
            serviceResult.setErrorModel(new ErrorModel(errorCode, "Exception@GetMyOrganizations " + ex.getMessage()));
        }

        return serviceResult;
    }

    @Override
    @Transactional
    public ServiceResult<OrganizationViewModel> addOrganization(OrganizationViewModel organizationViewModel,
                                                                long userId) {
        var serviceResult = new ServiceResult<OrganizationViewModel>();
        Optional<Integer> errorCode = Optional.empty();

        try {
            var exists = m_organizationRepository.existsByCode(organizationViewModel.getCode());

            if (exists) {
                errorCode = Optional.of(ErrorCodes.ORGANIZATION_EXISTS.getCode());
                throw new Exception("Organization already exists");
            }

            organizationViewModel.setCreatedDatetime(LocalDateTime.now());
            organizationViewModel.setActive(true);
            organizationViewModel.setDeleted(false);

            var user = m_userRepository.findById(userId);

            if (user.isEmpty()) {
                errorCode = Optional.of(ErrorCodes.USER_NOT_FOUND.getCode());
                throw new Exception("User not found");
            }

            organizationViewModel.setCreatedUserId(user.get().getId());

            var organization = m_mapper.organizationViewModelToOrganization(organizationViewModel);

            m_organizationRepository.save(organization);

            var userRole = new UserRole();

            var roleName = com.arslan.invitationapp.invitationapp.enums.Role.ADMIN.name();
            var role = m_roleRepository.findByName(roleName);

            if (role.isEmpty()) {
                var adminRole = new Role();
                adminRole.setName(roleName);
                adminRole.setActive(true);
                adminRole.setDeleted(false);
                adminRole.setCreatedDatetime(LocalDateTime.now());

                m_roleRepository.save(adminRole);

                role = Optional.of(adminRole);
            }

            userRole.setOrganization(organization);
            userRole.setRole(role.get());
            userRole.setUser(organization.getCreatedUser());
            userRole.setCreatedDatetime(LocalDateTime.now());
            userRole.setActive(true);
            userRole.setDeleted(false);

            m_userRoleRepository.save(userRole);

            serviceResult.setData(m_mapper.organizationToOrganizationViewModel(organization));
            serviceResult.setResponseStatus(ResponseStatus.OK);
        } catch (Throwable ex) {
            if (errorCode.isEmpty())
                errorCode = Optional.of(ErrorCodes.UNKNOWN_ERROR.getCode());

            serviceResult.setResponseStatus(ResponseStatus.FAIL);
            serviceResult.setErrorModel(new ErrorModel(errorCode, "Exception@addOrganization" + ex.getMessage()));
        }

        return serviceResult;
    }

    @Transactional
    @Override
    public ServiceResult<Boolean> removeOrganizationById(long organizationId, long currentUserId) {
        var serviceResult = new ServiceResult<Boolean>();
        Optional<Integer> errorCode = Optional.empty();

        //When the organization is deleted, we must delete the guests as well.
        try {
            var organization = m_organizationRepository.findById(organizationId);

            if (organization.isEmpty()) {
                errorCode = Optional.of(ErrorCodes.ORGANIZATION_NOT_FOUND.getCode());
                throw new Exception("Organization not found");
            }

            if (organization.get().getCreatedUser().getId() != currentUserId) {
                errorCode = Optional.of(ErrorCodes.NO_PERMISSION.getCode());
                throw new Exception("No Permission!!");
            }

            organization.get().setActive(false);
            organization.get().setDeleted(true);

            var organizationGuests = organization.get().getGuests();

            organizationGuests.forEach(g -> {
                g.setDeleted(true);
                g.setActive(false);
            });

            m_organizationRepository.save(organization.get());

            serviceResult.setData(true);
            serviceResult.setResponseStatus(ResponseStatus.OK);
        } catch (Throwable ex) {
            if (errorCode.isEmpty())
                errorCode = Optional.of(ErrorCodes.UNKNOWN_ERROR.getCode());

            serviceResult.setResponseStatus(ResponseStatus.FAIL);
            serviceResult.setErrorModel(new ErrorModel(errorCode, "Exception@removeOrganizationById" + ex.getMessage()));
        }

        return serviceResult;
    }
}