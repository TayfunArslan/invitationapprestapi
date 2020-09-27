package com.arslan.invitationapp.invitationapp.controller;

import com.arslan.invitationapp.invitationapp.service.Interface.IUserService;
import com.arslan.invitationapp.invitationapp.enums.ResponseStatus;
import com.arslan.invitationapp.invitationapp.viewmodel.UserViewModel;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {
    private final IUserService m_userService;

    public UserController(IUserService userService) {
        m_userService = userService;
    }

    @PostMapping("/save")
    public ResponseEntity<UserViewModel> saveUser(@RequestBody UserViewModel userViewModel) {
        var serviceResult = m_userService.AddUser(userViewModel);

        if(serviceResult.getResponseStatus() != ResponseStatus.OK)
            return ResponseEntity.badRequest().body(serviceResult.getData());

        return ResponseEntity.ok(serviceResult.getData());
    }
}
