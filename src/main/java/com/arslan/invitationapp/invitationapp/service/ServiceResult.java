package com.arslan.invitationapp.invitationapp.service;

enum ResponseStatus {
    OK, FAIL
}

public class ServiceResult<T> {
    private T data;
    private ResponseStatus responseStatus;
    private String message;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
