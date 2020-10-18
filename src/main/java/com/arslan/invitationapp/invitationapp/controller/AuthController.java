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
    private final AuthenticationManager m_authenticationManager;
    private final IUserService m_userService;
    private final IRoleService m_roleService;
    private final IUserRoleService m_userRoleService;
    private final PasswordEncoder m_encoder;
    private final JwtUtils m_jwtUtils;

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
        var jwt = m_jwtUtils.generateJwtToken(loginRequestModel.getUsername());

        var user = m_userService.getUserByUsername(loginRequestModel.getUsername());

        if(user.getData().getId() == 0)
            return ResponseEntity.badRequest().body(new UserViewModel());

        return ResponseEntity.ok(new JwtResponseModel(jwt,
                (long) user.getData().getId(),
                user.getData().getUsername(),
                user.getData().getEmail()));
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
