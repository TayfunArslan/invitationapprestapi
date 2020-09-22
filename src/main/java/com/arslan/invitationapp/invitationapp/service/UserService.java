package com.arslan.invitationapp.invitationapp.service;

import com.arslan.invitationapp.invitationapp.data.repository.IUserRepository;
import com.arslan.invitationapp.invitationapp.mapper.IMapper;
import com.arslan.invitationapp.invitationapp.service.Interface.IUserService;
import com.arslan.invitationapp.invitationapp.viewmodel.UserViewModel;
import org.springframework.stereotype.Service;

@Service
public class UserService  implements IUserService {
    private IUserRepository m_userRepository;
    private IMapper m_mapper;

    public UserService(IUserRepository userRepository, IMapper mapper) {
        m_userRepository = userRepository;
        m_mapper = mapper;
    }

    @Override
    public ServiceResult<UserViewModel> setCoOwner(UserViewModel coOwner) {
        var serviceResult = new ServiceResult<UserViewModel>();

        try {
            var user = m_userRepository.save(m_mapper.userViewModelToUser(coOwner));

            serviceResult.setData(m_mapper.userToUserViewModel(user));
            serviceResult.setResponseStatus(ResponseStatus.OK);
        } catch (Throwable ex) {
            serviceResult.setData(new UserViewModel());
            serviceResult.setResponseStatus(ResponseStatus.FAIL);
            serviceResult.setMessage("Exception@setCoOwner" + ex.getMessage());
        }

        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> removeCoOwner(long userId) {
        var serviceResult = new ServiceResult<Boolean>();

        try{
            var user = m_userRepository.findById(userId);
            user.ifPresent(u -> m_userRepository.delete(u));

            serviceResult.setData(true);
            serviceResult.setResponseStatus(ResponseStatus.OK);
            serviceResult.setMessage("");
        } catch (Throwable ex) {
            serviceResult.setData(false);
            serviceResult.setMessage(ex.getMessage());
            serviceResult.setResponseStatus(ResponseStatus.FAIL);
        }

        return serviceResult;
    }
}
