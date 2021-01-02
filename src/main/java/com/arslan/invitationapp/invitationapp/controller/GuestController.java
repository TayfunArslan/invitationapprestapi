package com.arslan.invitationapp.invitationapp.controller;

import com.arslan.invitationapp.invitationapp.enums.ResponseStatus;
import com.arslan.invitationapp.invitationapp.service.Interface.IGuestService;
import com.arslan.invitationapp.invitationapp.service.Interface.IUserService;
import com.arslan.invitationapp.invitationapp.viewmodel.GuestViewModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/guest")
public class GuestController extends BaseController {
    private final IGuestService m_guestService;

    public GuestController(IGuestService guestService, IUserService userService1) {
        m_guestService = guestService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> addGuestToOrganization(@RequestBody GuestViewModel guestModel) {
        var currentUserId = getCurrentUserId();

        var serviceResult = m_guestService.addGuest(guestModel, currentUserId);

        if (serviceResult.getResponseStatus() == ResponseStatus.FAIL)
            return ResponseEntity.badRequest().body(serviceResult.getErrorModel());

        return ResponseEntity.ok(serviceResult.getData());
    }

    @PostMapping("/delete/{guestId}")
    public ResponseEntity<Boolean> removeGuestFromOrganization(@PathVariable long guestId) {
        var serviceResult = m_guestService.removeGuest(guestId);

        if(serviceResult.getResponseStatus() == ResponseStatus.FAIL)
            return ResponseEntity.badRequest().body(serviceResult.getData());

        return ResponseEntity.ok(serviceResult.getData());
    }
}