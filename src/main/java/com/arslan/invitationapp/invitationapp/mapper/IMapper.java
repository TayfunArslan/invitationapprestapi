package com.arslan.invitationapp.invitationapp.mapper;

import com.arslan.invitationapp.invitationapp.data.entity.*;
import com.arslan.invitationapp.invitationapp.viewmodel.*;

public interface IMapper {
    User userViewModelToUser(UserViewModel userViewModel);

    UserViewModel userToUserViewModel(User user);

    Organization organizationViewModelToOrganization(OrganizationViewModel organizationViewModel);

    OrganizationViewModel organizationToOrganizationViewModel(Organization organization);

    Guest guestViewModelToGuest(GuestViewModel guestViewModel);

    GuestViewModel guestToGuestViewModel(Guest guest);

    OrganizationCoOwnerViewModel organizationCoOwnerToOrganizationCoOwnerViewModel(OrganizationCoOwner organizationCoOwner);

    OrganizationCoOwner organizationCoOwnerViewModelToOrganizationCoOwner(OrganizationCoOwnerViewModel organizationCoOwnerViewModel);

    Role roleViewModelToRole(RoleViewModel roleViewModel);

    RoleViewModel roleToRoleViewModel(Role role);
}
