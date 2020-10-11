package com.arslan.invitationapp.invitationapp.controller;

import com.arslan.invitationapp.invitationapp.data.repository.IRoleRepository;
import com.arslan.invitationapp.invitationapp.enums.ResponseStatus;
import com.arslan.invitationapp.invitationapp.security.jwt.JwtUtils;
import com.arslan.invitationapp.invitationapp.service.Interface.IRoleService;
import com.arslan.invitationapp.invitationapp.service.Interface.IUserRoleService;
import com.arslan.invitationapp.invitationapp.service.Interface.IUserService;
import com.arslan.invitationapp.invitationapp.service.UserDetailsImpl;
import com.arslan.invitationapp.invitationapp.viewmodel.UserViewModel;
import com.arslan.invitationapp.invitationapp.viewmodel.request.LoginRequestModel;
import com.arslan.invitationapp.invitationapp.viewmodel.request.SignupRequestModel;
import com.arslan.invitationapp.invitationapp.viewmodel.response.JwtResponseModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@CrossOrigin(origins = "+", maxAge = 3600)
@RestController
@RequestMapping("api/auth")
public class AuthController {
    AuthenticationManager m_authenticationManager;
    IUserService m_userService;
    IRoleService m_roleService;
    IUserRoleService m_userRoleService;
    PasswordEncoder m_encoder;
    JwtUtils m_jwtUtils;

    public AuthController(AuthenticationManager authenticationManager,
                          IUserService userService,
                          IRoleService roleService,
                          IUserRoleService userRoleService,
                          PasswordEncoder encoder,
                          JwtUtils jwtUtils) {
        m_authenticationManager = authenticationManager;
        m_userService = userService;
        m_roleService = roleService;
        m_userRoleService = userRoleService;
        m_encoder = encoder;
        m_jwtUtils = jwtUtils;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequestModel loginRequestModel) {
        var authentication = m_authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequestModel.getUsername(),
                        loginRequestModel.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        var jwt = m_jwtUtils.generateJwtToken(authentication);

        var userDetails = (UserDetailsImpl) authentication.getPrincipal();
        var roles = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponseModel(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<UserViewModel> registerUser(@Valid @RequestBody UserViewModel userViewModel) {
        var serviceResult = m_userService.addUser(userViewModel);

        if(serviceResult.getResponseStatus() == ResponseStatus.FAIL)
            return ResponseEntity.badRequest().body(serviceResult.getData());

        return ResponseEntity.ok(serviceResult.getData());
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }
}