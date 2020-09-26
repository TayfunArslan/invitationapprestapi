package com.arslan.invitationapp.invitationapp.service;

import com.arslan.invitationapp.invitationapp.data.repository.IGuestRepository;
import com.arslan.invitationapp.invitationapp.data.repository.IUserRepository;
import com.arslan.invitationapp.invitationapp.enums.ResponseStatus;
import com.arslan.invitationapp.invitationapp.mapper.IMapper;
import com.arslan.invitationapp.invitationapp.service.Interface.IGuestService;
import com.arslan.invitationapp.invitationapp.viewmodel.GuestViewModel;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Service;

@Service
public class GuestService implements IGuestService {
    private IGuestRepository m_guestRepository;
    private IUserRepository m_userRepository;
    private IMapper m_mapper;

    public GuestService(IGuestRepository guestRepository, IUserRepository userRepository, IMapper mapper) {
        m_guestRepository = guestRepository;
        m_userRepository = userRepository;
        m_mapper = mapper;
    }

    public ServiceResult<GuestViewModel> addGuest(GuestViewModel guestViewModel) {
        var serviceResult = new ServiceResult<GuestViewModel>();

        try {
            var guestIsExist =
                    m_guestRepository.existsGuestByNameAndSurnameAndOrganizationId(guestViewModel.getName(),
                            guestViewModel.getSurname(), guestViewModel.getOrganizationId());

            if(guestIsExist)
                throw new Exception("User already invited");

            var guest = m_guestRepository.save(m_mapper.guestViewModelToGuest(guestViewModel));

            serviceResult.setData(m_mapper.guestToGuestViewModel(guest));
            serviceResult.setResponseStatus(ResponseStatus.OK);
        } catch (Throwable ex) {
            serviceResult.setData(new GuestViewModel());
            serviceResult.setMessage("Exception@addGuestToOrganization" + ex.getMessage());
            serviceResult.setResponseStatus(ResponseStatus.FAIL);
        }

        return serviceResult;
    }

    public ServiceResult<Boolean> removeGuest(long guestId) {
        var serviceResult = new ServiceResult<Boolean>();

        try {
            m_guestRepository.deleteById(guestId);

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
