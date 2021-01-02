package com.arslan.invitationapp.invitationapp.viewmodel.serviceResult;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorModel {
    private Optional<Integer> errorCode;
    private String message;
}
