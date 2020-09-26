package com.arslan.invitationapp.invitationapp.data.repository;

import com.arslan.invitationapp.invitationapp.data.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IGuestRepository extends JpaRepository<Guest, Long> {
    boolean existsGuestByNameAndSurnameAndOrganizationId(String name, String surname, long organizationId);
}
