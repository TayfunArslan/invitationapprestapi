package com.arslan.invitationapp.invitationapp.mapper;

import com.arslan.invitationapp.invitationapp.data.entity.*;
import com.arslan.invitationapp.invitationapp.viewmodel.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(implementationName = "Mapper", componentModel = "spring")
public  interface IMapper {
    User userViewModelToUser(UserViewModel userViewModel);
    UserViewModel userToUserViewModel(User user);

    Guest guestViewModelToGuest(GuestViewModel guestViewModel);
    GuestViewModel guestToGuestViewModel(Guest guest);

    Organization organizationViewModelToOrganization(OrganizationViewModel organizationViewModel);
    OrganizationViewModel organizationToOrganizationViewModel(Organization organization);

    OrganizationCoOwnerViewModel organizationCoOwnerToOrganizationCoOwnerViewModel(OrganizationCoOwner organizationCoOwner);
    OrganizationCoOwner organizationCoOwnerViewModelToOrganizationCoOwner(OrganizationCoOwnerViewModel organizationCoOwnerViewModel);

    Role roleViewModelToRole(RoleViewModel roleViewModel);
    RoleViewModel roleToRoleViewModel(Role role);
}
