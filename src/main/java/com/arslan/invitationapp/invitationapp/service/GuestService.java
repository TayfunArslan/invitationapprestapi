package com.arslan.invitationapp.invitationapp.service;

import com.arslan.invitationapp.invitationapp.data.repository.IGuestRepository;
import com.arslan.invitationapp.invitationapp.data.repository.IUserRepository;
import com.arslan.invitationapp.invitationapp.enums.ResponseStatus;
import com.arslan.invitationapp.invitationapp.mapper.IMapper;
import com.arslan.invitationapp.invitationapp.service.Interface.IGuestService;
import com.arslan.invitationapp.invitationapp.viewmodel.GuestViewModel;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GuestService implements IGuestService {
    private final IGuestRepository m_guestRepository;
    private final IUserRepository m_userRepository;
    private final IMapper m_mapper;

    public GuestService(IGuestRepository guestRepository, IUserRepository userRepository, IMapper mapper) {
        m_guestRepository = guestRepository;
        m_userRepository = userRepository;
        m_mapper = mapper;
    }

    @Override
    public ServiceResult<List<GuestViewModel>> getAllByOrganizationId(long organizationId) {
        var serviceResult = new ServiceResult<List<GuestViewModel>>();

//        try {
//            var guestList = m_guestRepository.getAllByOrganizationId(organizationId)
//                    .stream()
//                    .map(m_mapper::guestToGuestViewModel)
//                    .collect(Collectors.toList());
//
//            serviceResult.setData(guestList);
//            serviceResult.setResponseStatus(ResponseStatus.OK);
//        } catch (Throwable ex) {
//            serviceResult.setData(new ArrayList<>());
//            serviceResult.setResponseStatus(ResponseStatus.FAIL);
//            serviceResult.setMessage(ex.getMessage());
//        }

        return serviceResult;
    }

    @Override
    public ServiceResult<GuestViewModel> addGuest(GuestViewModel guestViewModel) {
        var serviceResult = new ServiceResult<GuestViewModel>();

        try {
            var guestIsExist =
                    m_guestRepository.existsGuestByNameAndSurnameAndOrganizationId(guestViewModel.getName(),
                            guestViewModel.getSurname(), guestViewModel.getOrganizationId());

            if (guestIsExist)
                throw new Exception("User already invited");

            guestViewModel.setCreatedDatetime(LocalDateTime.now());
            guestViewModel.setActive(true);
            guestViewModel.setDeleted(false);

            var guestEntity = m_mapper.guestViewModelToGuest(guestViewModel);
            var guest = m_guestRepository.save(guestEntity);

            serviceResult.setData(m_mapper.guestToGuestViewModel(guest));
            serviceResult.setResponseStatus(ResponseStatus.OK);
        } catch (Throwable ex) {
            serviceResult.setData(new GuestViewModel());
            serviceResult.setMessage("Exception@addGuestToOrganization" + ex.getMessage());
            serviceResult.setResponseStatus(ResponseStatus.FAIL);
        }

        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> removeGuest(long guestId) {
        var serviceResult = new ServiceResult<Boolean>();

        try {
            var guest = m_guestRepository.findById(guestId);

            if (guest.isEmpty())
                throw new Exception("Guest not found");

            guest.get().setActive(false);
            guest.get().setDeleted(true);

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
