package com.arslan.invitationapp.invitationapp.mapper;

import com.arslan.invitationapp.invitationapp.data.entity.*;
import com.arslan.invitationapp.invitationapp.data.repository.*;
import com.arslan.invitationapp.invitationapp.viewmodel.*;
import org.springframework.stereotype.Component;

@Component
public class Mapper implements IMapper {
    private final IUserRepository m_userRepository;
    private final IGuestRepository m_guestRepository;
    private final IUserRoleRepository m_userRoleRepository;
    private final IOrganizationRepository m_organizationRepository;
    private final IRoleRepository m_roleRepository;
    private final IOrganizationCoOwnerRepository m_organizationCoOwnerRepository;

    public Mapper(IUserRepository userRepository,
                  IGuestRepository guestRepository,
                  IUserRoleRepository userRoleRepository,
                  IOrganizationRepository organizationRepository,
                  IRoleRepository roleRepository,
                  IOrganizationCoOwnerRepository organizationCoOwnerRepository) {
        m_userRepository = userRepository;
        m_guestRepository = guestRepository;
        m_userRoleRepository = userRoleRepository;
        m_organizationRepository = organizationRepository;
        m_roleRepository = roleRepository;
        m_organizationCoOwnerRepository = organizationCoOwnerRepository;
    }


    public User userViewModelToUser(UserViewModel userViewModel) {
        if (userViewModel == null)
            return null;

        var user = new User();
        user.setId(userViewModel.getId());
        user.setActive(userViewModel.isActive());
        user.setDeleted(userViewModel.isDeleted());
        user.setCreatedDatetime(userViewModel.getCreatedDatetime());
        user.setPhone(userViewModel.getPhone());
        user.setName(userViewModel.getName());
        user.setUsername(userViewModel.getUsername());
        user.setPassword(userViewModel.getPassword());
        user.setSurname(userViewModel.getSurname());
        user.setEmail(userViewModel.getEmail());

        return user;
    }

    public UserViewModel userToUserViewModel(User user) {
        if (user == null)
            return null;
        var userViewModel = new UserViewModel();
        userViewModel.setId(user.getId());
        userViewModel.setName(user.getName());
        userViewModel.setSurname(user.getSurname());
        userViewModel.setUsername(user.getUsername());
        userViewModel.setPassword(user.getPassword());
        userViewModel.setEmail(user.getEmail());
        userViewModel.setPhone(user.getPhone());
        userViewModel.setCreatedDatetime(user.getCreatedDatetime());
        userViewModel.setActive(user.isActive());
        userViewModel.setDeleted(user.isDeleted());

        return userViewModel;
    }

    public Guest guestViewModelToGuest(GuestViewModel guestViewModel) {
        if (guestViewModel == null)
            return null;

        var guest = new Guest();
        guest.setId(guestViewModel.getId());
        guest.setActive(guestViewModel.isActive());
        guest.setDeleted(guestViewModel.isDeleted());
        guest.setCreatedDatetime(guestViewModel.getCreatedDatetime());
        guest.setName(guestViewModel.getName());
        guest.setSurname(guestViewModel.getSurname());
        guest.setCalled(guestViewModel.isCalled());
        guest.setMailSent(guestViewModel.isMailSent());
        guest.setWillCome(guestViewModel.isWillCome());
        guest.setPhone(guestViewModel.getPhone());
        guest.setEmail(guestViewModel.getEmail());

        guest.setOrganization(m_organizationRepository.getOne(guestViewModel.getOrganizationId()));
        guest.setInviter(m_userRepository.getOne(guestViewModel.getInviterId()));

        return guest;
    }

    public GuestViewModel guestToGuestViewModel(Guest guest) {
        if (guest == null)
            return null;

        var guestViewModel = new GuestViewModel();
        guestViewModel.setId(guest.getId());
        guestViewModel.setName(guest.getName());
        guestViewModel.setSurname(guest.getSurname());
        guestViewModel.setCalled(guest.isCalled());
        guestViewModel.setMailSent(guest.isMailSent());
        guestViewModel.setWillCome(guest.isWillCome());
        guestViewModel.setActive(guest.isActive());
        guestViewModel.setDeleted(guest.isDeleted());
        guestViewModel.setCreatedDatetime(guest.getCreatedDatetime());
        guestViewModel.setOrganizationId(guest.getOrganization().getId());
        guestViewModel.setInviterId(guest.getInviter().getId());
        guestViewModel.setPhone(guest.getPhone());
        guestViewModel.setEmail(guest.getEmail());

        return guestViewModel;
    }

    public Organization organizationViewModelToOrganization(OrganizationViewModel organizationViewModel) {
        if (organizationViewModel == null)
            return null;

        var organization = new Organization();
        organization.setId(organizationViewModel.getId());
        organization.setCreatedUser(m_userRepository.getOne(organizationViewModel.getCreatedUserId()));
        organization.setActive(organizationViewModel.isActive());
        organization.setDeleted(organizationViewModel.isDeleted());
        organization.setCode(organizationViewModel.getCode());
        organization.setName(organizationViewModel.getName());
        organization.setCreatedDatetime(organizationViewModel.getCreatedDatetime());

        return organization;
    }

    public OrganizationViewModel organizationToOrganizationViewModel(Organization organization) {
        if (organization == null)
            return null;

        //Good looking but slow.
        return new OrganizationViewModel() {{
            setDeleted(organization.isActive());
            setActive(organization.isActive());
            setCreatedDatetime(organization.getCreatedDatetime());
            setCreatedUserId(organization.getCreatedUser().getId());
            setCode(organization.getCode());
            setId(organization.getId());
            setName(organization.getName());
        }};
    }

    public OrganizationCoOwnerViewModel organizationCoOwnerToOrganizationCoOwnerViewModel(OrganizationCoOwner organizationCoOwner) {
        if (organizationCoOwner == null)
            return null;

        var organizationCoOwnerViewModel = new OrganizationCoOwnerViewModel();
        organizationCoOwnerViewModel.setId(organizationCoOwner.getId());
        organizationCoOwnerViewModel.setActive(organizationCoOwner.isActive());
        organizationCoOwnerViewModel.setDeleted(organizationCoOwner.isDeleted());
        organizationCoOwnerViewModel.setOrganizationId(organizationCoOwner.getOrganization().getId());
        organizationCoOwnerViewModel.setRoleId(organizationCoOwner.getRole().getId());
        organizationCoOwnerViewModel.setCoOwnerId(organizationCoOwner.getCoOwner().getId());

        return organizationCoOwnerViewModel;
    }

    public OrganizationCoOwner organizationCoOwnerViewModelToOrganizationCoOwner(OrganizationCoOwnerViewModel organizationCoOwnerViewModel) {
        if (organizationCoOwnerViewModel == null)
            return null;

        var organizationCoOwner = new OrganizationCoOwner();
        organizationCoOwner.setId(organizationCoOwnerViewModel.getId());
        organizationCoOwner.setActive(organizationCoOwnerViewModel.isActive());
        organizationCoOwner.setDeleted(organizationCoOwnerViewModel.isDeleted());
        organizationCoOwner.setOrganization(m_organizationRepository.getOne(organizationCoOwnerViewModel.getOrganizationId()));
        organizationCoOwner.setRole(m_roleRepository.getOne(organizationCoOwnerViewModel.getRoleId()));
        organizationCoOwner.setCoOwner(m_userRepository.getOne(organizationCoOwnerViewModel.getCoOwnerId()));

        return organizationCoOwner;
    }

    public Role roleViewModelToRole(RoleViewModel roleViewModel) {
        if (roleViewModel == null)
            return null;

        var role = new Role();
        role.setId(roleViewModel.getId());
        role.setActive(roleViewModel.isActive());
        role.setDeleted(roleViewModel.isDeleted());
        role.setCreatedDatetime(roleViewModel.getCreatedDatetime());
        role.setName(roleViewModel.getName());

        return role;
    }

    public RoleViewModel roleToRoleViewModel(Role role) {
        if (role == null)
            return null;

        var roleViewModel = new RoleViewModel();
        roleViewModel.setId(role.getId());
        roleViewModel.setName(role.getName());
        roleViewModel.setCreatedDatetime(role.getCreatedDatetime());
        roleViewModel.setActive(role.isActive());
        roleViewModel.setDeleted(role.isDeleted());

        return roleViewModel;
    }
}