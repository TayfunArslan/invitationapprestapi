package com.arslan.invitationapp.invitationapp.service.Interface;

import com.arslan.invitationapp.invitationapp.viewmodel.serviceResult.ServiceResult;
import com.arslan.invitationapp.invitationapp.viewmodel.OrganizationViewModel;

import java.util.List;

public interface IOrganizationService {
    ServiceResult<List<OrganizationViewModel>> getMyOrganizations(long currentUserId);

    ServiceResult<OrganizationViewModel> addOrganization(OrganizationViewModel organizationViewModel,
                                                         long currentUserId);

    ServiceResult<Boolean> removeOrganizationById(long id, long currentUserId);
}
