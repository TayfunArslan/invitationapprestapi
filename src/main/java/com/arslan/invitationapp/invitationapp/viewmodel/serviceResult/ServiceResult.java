package com.arslan.invitationapp.invitationapp.viewmodel.serviceResult;

import com.arslan.invitationapp.invitationapp.enums.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceResult<T> {
    private T data;
    private ResponseStatus responseStatus;
    private ErrorModel errorModel;
}

