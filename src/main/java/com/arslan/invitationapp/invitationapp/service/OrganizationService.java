package com.arslan.invitationapp.invitationapp.service;

import com.arslan.invitationapp.invitationapp.data.repository.IOrganizationRepository;
import com.arslan.invitationapp.invitationapp.enums.ResponseStatus;
import com.arslan.invitationapp.invitationapp.mapper.IMapper;
import com.arslan.invitationapp.invitationapp.service.Interface.IOrganizationService;
import com.arslan.invitationapp.invitationapp.viewmodel.OrganizationViewModel;
import org.springframework.stereotype.Service;

@Service
public class OrganizationService implements IOrganizationService {
    private final IOrganizationRepository m_organizationRepository;
    private final IMapper m_mapper;

    public OrganizationService(IOrganizationRepository organizationRepository, IMapper mapper) {
        m_organizationRepository = organizationRepository;
        m_mapper = mapper;
    }

    @Override
    public ServiceResult<OrganizationViewModel> addOrganization(OrganizationViewModel organizationViewModel) {
        var serviceResult = new ServiceResult<OrganizationViewModel>();

        try {
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

    @Override
    public ServiceResult<Boolean> removeOrganizationById(long id) {
        var serviceResult = new ServiceResult<Boolean>();

        try {
            m_organizationRepository.deleteById(id);

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
