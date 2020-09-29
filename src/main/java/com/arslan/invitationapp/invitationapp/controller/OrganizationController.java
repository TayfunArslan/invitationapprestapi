package com.arslan.invitationapp.invitationapp.controller;

import com.arslan.invitationapp.invitationapp.enums.ResponseStatus;
import com.arslan.invitationapp.invitationapp.service.Interface.IOrganizationService;
import com.arslan.invitationapp.invitationapp.viewmodel.OrganizationViewModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/organization")
public class OrganizationController {
    private final IOrganizationService m_organizationService;

    public OrganizationController(IOrganizationService organizationService) {
        m_organizationService = organizationService;
    }

    @PostMapping("/save")
    public ResponseEntity<OrganizationViewModel> addOrganization( @RequestBody OrganizationViewModel organizationViewModel) {
        var serviceResult = m_organizationService.addOrganization(organizationViewModel);

        if(serviceResult.getResponseStatus() == ResponseStatus.FAIL)
            return ResponseEntity.badRequest().body(new OrganizationViewModel());

        return ResponseEntity.ok(serviceResult.getData());
    }

    @PostMapping("/delete/{organizationId}")
    public ResponseEntity<Boolean> removeOrganization(@PathVariable long organizationId) {
        var serviceResult = m_organizationService.removeOrganizationById(organizationId);

        if(serviceResult.getResponseStatus() == ResponseStatus.FAIL)
            return ResponseEntity.badRequest().body(serviceResult.getData());

        return ResponseEntity.ok(serviceResult.getData());
    }
}
