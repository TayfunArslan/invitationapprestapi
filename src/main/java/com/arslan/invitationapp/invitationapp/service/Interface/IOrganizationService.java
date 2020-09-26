package com.arslan.invitationapp.invitationapp.service.Interface;

import com.arslan.invitationapp.invitationapp.service.ServiceResult;
import com.arslan.invitationapp.invitationapp.viewmodel.OrganizationViewModel;

public interface IOrganizationService {
    ServiceResult<OrganizationViewModel> addOrganization( OrganizationViewModel organizationViewModel);
}
