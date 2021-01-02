package com.arslan.invitationapp.invitationapp.controller;

import com.arslan.invitationapp.invitationapp.enums.ResponseStatus;
import com.arslan.invitationapp.invitationapp.security.jwt.JwtUtils;
import com.arslan.invitationapp.invitationapp.service.Interface.IRoleService;
import com.arslan.invitationapp.invitationapp.service.Interface.IUserRoleService;
import com.arslan.invitationapp.invitationapp.service.Interface.IUserService;
import com.arslan.invitationapp.invitationapp.viewmodel.UserViewModel;
import com.arslan.invitationapp.invitationapp.viewmodel.request.LoginRequestModel;
import com.arslan.invitationapp.invitationapp.viewmodel.response.JwtResponseModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "+", maxAge = 3600)
@RestController
@RequestMapping("api/auth")
public class AuthController {
    private final IUserService m_userService;
    private final JwtUtils m_jwtUtils;

    public AuthController(IUserService userService, JwtUtils jwtUtils) {
        m_userService = userService;
        m_jwtUtils = jwtUtils;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequestModel loginRequestModel) {
        var user = m_userService.login(loginRequestModel.getUsername(), loginRequestModel.getPassword());

        if(user.getResponseStatus() == ResponseStatus.FAIL)
            return ResponseEntity.badRequest().body(user.getErrorModel());

        var jwt = m_jwtUtils.generateJwtToken(loginRequestModel.getUsername());

        return ResponseEntity.ok(new JwtResponseModel(jwt,
                user.getData().getId(),
                user.getData().getUsername(),
                user.getData().getEmail()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserViewModel userViewModel) {
        var serviceResult = m_userService.addUser(userViewModel);

        if(serviceResult.getResponseStatus() == ResponseStatus.FAIL)
            return ResponseEntity.badRequest().body(serviceResult.getErrorModel());

        return ResponseEntity.ok(serviceResult.getData());
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }
}
