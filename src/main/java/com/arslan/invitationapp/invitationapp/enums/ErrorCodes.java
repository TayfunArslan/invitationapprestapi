package com.arslan.invitationapp.invitationapp.enums;

import lombok.Getter;
import lombok.Setter;

public enum ErrorCodes {
    USERNAME_EXIST(1),
    MAIL_EXIST(2),
    USER_NOT_FOUND(3),
    ORGANIZATION_NOT_FOUND(4),
    ORGANIZATION_EXISTS(5),
    GUEST_NOT_FOUND(6),
    GUEST_ALREADY_INVITED(7),
    WRONG_PASSWORD(8),
    NO_PERMISSION(9),
    UNKNOWN_ERROR(20);

    @Getter
    @Setter
    private int code;

    ErrorCodes(int code) {
        this.code = code;
    }
}
