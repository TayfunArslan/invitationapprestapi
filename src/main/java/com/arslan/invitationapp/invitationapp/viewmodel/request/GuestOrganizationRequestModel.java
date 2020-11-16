package com.arslan.invitationapp.invitationapp.viewmodel.request;

import com.arslan.invitationapp.invitationapp.viewmodel.GuestViewModel;

public class GuestOrganizationRequestModel {
    private GuestViewModel guest;
    private int organizationId;

    public GuestOrganizationRequestModel() {}

    public GuestOrganizationRequestModel(GuestViewModel guest, int organizationId) {
        this.guest = guest;
        this.organizationId = organizationId;
    }

    public GuestViewModel getGuest() {
        return guest;
    }

    public void setGuest(GuestViewModel guest) {
        this.guest = guest;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }
}
