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
    public ResponseEntity<?> saveUser(@RequestBody UserViewModel userViewModel) {
        var serviceResult = m_userService.addUser(userViewModel);

        if(serviceResult.getResponseStatus() == ResponseStatus.FAIL)
            return ResponseEntity.badRequest().body(serviceResult.getData());

        return ResponseEntity.ok(serviceResult.getData());
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

//    @GetMapping("/AllUser/{id}")
//    public ResponseEntity<List<UserViewModel>> getAllUserByOrganizationId(@PathVariable long id) {
//        var serviceResult = m_userService.
//    }
}
