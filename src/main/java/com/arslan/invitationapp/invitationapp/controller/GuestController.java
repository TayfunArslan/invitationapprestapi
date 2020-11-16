package com.arslan.invitationapp.invitationapp.controller;

import com.arslan.invitationapp.invitationapp.enums.ResponseStatus;
import com.arslan.invitationapp.invitationapp.service.Interface.IGuestService;
import com.arslan.invitationapp.invitationapp.viewmodel.GuestViewModel;
import com.arslan.invitationapp.invitationapp.viewmodel.request.GuestOrganizationRequestModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/guest")
public class GuestController {
    private final IGuestService m_guestService;

    public GuestController(IGuestService guestService) {
        m_guestService = guestService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> addGuestToOrganization(GuestViewModel guestModel) {
        var serviceResult = m_guestService.addGuest(guestModel);

        if (serviceResult.getResponseStatus() == ResponseStatus.FAIL)
            return ResponseEntity.badRequest().body(serviceResult.getMessage());

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
