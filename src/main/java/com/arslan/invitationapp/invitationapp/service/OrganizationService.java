package com.arslan.invitationapp.invitationapp.service;

import com.arslan.invitationapp.invitationapp.data.entity.Organization;
import com.arslan.invitationapp.invitationapp.data.repository.IGuestRepository;
import com.arslan.invitationapp.invitationapp.data.repository.IOrganizationRepository;
import com.arslan.invitationapp.invitationapp.data.repository.IUserRepository;
import com.arslan.invitationapp.invitationapp.enums.ResponseStatus;
import com.arslan.invitationapp.invitationapp.mapper.IMapper;
import com.arslan.invitationapp.invitationapp.service.Interface.IOrganizationService;
import com.arslan.invitationapp.invitationapp.viewmodel.OrganizationViewModel;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrganizationService implements IOrganizationService {
    private final IOrganizationRepository m_organizationRepository;
    private final IUserRepository m_userRepository;
    private final IMapper m_mapper;

    public OrganizationService(IOrganizationRepository organizationRepository,
                               IUserRepository userRepository,
                               IMapper mapper) {
        m_organizationRepository = organizationRepository;
        m_userRepository = userRepository;
        m_mapper = mapper;
    }

    public ServiceResult<List<OrganizationViewModel>> getMyOrganizations(long currentUserId) {
        var serviceResult = new ServiceResult<List<OrganizationViewModel>>();

        try {
            var organizations =
                    m_organizationRepository
                            .findAll()
                            .stream()
                            .filter(o -> o.getCreatedUser().getId() == currentUserId)
                            .collect(Collectors.toList());
            
            serviceResult.setData(organizations.stream().map(m_mapper::organizationToOrganizationViewModel).collect(Collectors.toList()));
            serviceResult.setResponseStatus(ResponseStatus.OK);
        } catch (Throwable ex) {
            serviceResult.setResponseStatus(ResponseStatus.FAIL);
            serviceResult.setMessage(ex.getMessage()+ " Exception@getMyOrganization");
        }

        return serviceResult;
    }

    @Override
    public ServiceResult<OrganizationViewModel> addOrganization(OrganizationViewModel organizationViewModel,
                                                                long userId) {
        //Organizasyon atandığında roller de atanmalı. Oluşturan kişi
        var serviceResult = new ServiceResult<OrganizationViewModel>();

        try {
            var user = m_userRepository.findById(userId);
            user.ifPresent(value -> organizationViewModel.setCreatedUser(m_mapper.userToUserViewModel(value)));
            organizationViewModel.setActive(true);
            organizationViewModel.setDeleted(false);
            organizationViewModel.setCreatedDatetime(LocalDateTime.now());

            var organization =
                    m_organizationRepository.save(m_mapper.organizationViewModelToOrganization(organizationViewModel));

            serviceResult.setData(m_mapper.organizationToOrganizationViewModel(organization));
            serviceResult.setResponseStatus(ResponseStatus.OK);
        } catch (Throwable ex) {
            serviceResult.setData(new OrganizationViewModel());
            serviceResult.setResponseStatus(ResponseStatus.FAIL);
            serviceResult.setMessage("Exception@createOrganization " + ex.getMessage());
        }

        return serviceResult;
    }

    @Transactional
    @Override
    public ServiceResult<Boolean> removeOrganizationById(long organizationId) {
        var serviceResult = new ServiceResult<Boolean>();

        //When the organization is deleted, we must delete the guests as well.
        try {
            var organization = m_organizationRepository.findById(organizationId);

            if (organization.isEmpty())

                throw new Exception("Organization not found");

            organization.get().setActive(false);
            organization.get().setDeleted(true);

            var organizationGuests = organization.get().getGuests();
//            var organizationGuests = m_guestRepository.getAllByOrganizationId(organizationId);

            organizationGuests.forEach(g -> {
                g.setDeleted(true);
                g.setActive(false);
            });

            serviceResult.setData(true);
            serviceResult.setResponseStatus(ResponseStatus.OK);
        } catch (Throwable ex) {
            serviceResult.setData(false);
            serviceResult.setResponseStatus(ResponseStatus.FAIL);
            serviceResult.setMessage(ex.getMessage());
        }

        return serviceResult;
    }
}
