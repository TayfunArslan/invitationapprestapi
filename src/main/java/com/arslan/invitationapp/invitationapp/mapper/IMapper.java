package com.arslan.invitationapp.invitationapp.mapper;

import com.arslan.invitationapp.invitationapp.data.entity.Guest;
import com.arslan.invitationapp.invitationapp.data.entity.Organization;
import com.arslan.invitationapp.invitationapp.data.entity.OrganizationCoOwner;
import com.arslan.invitationapp.invitationapp.data.entity.User;
import com.arslan.invitationapp.invitationapp.viewmodel.GuestViewModel;
import com.arslan.invitationapp.invitationapp.viewmodel.OrganizationCoOwnerViewModel;
import com.arslan.invitationapp.invitationapp.viewmodel.OrganizationViewModel;
import com.arslan.invitationapp.invitationapp.viewmodel.UserViewModel;
import org.mapstruct.Mapper;

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
}
