package com.arslan.invitationapp.invitationapp.data.repository;

import com.arslan.invitationapp.invitationapp.data.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IGuestRepository extends JpaRepository<Guest, Long> {
    List<Guest> getAllByOrganizationId(long organizationId);
    boolean existsGuestByNameAndSurnameAndOrganizationId(String name, String surname, long organizationId);
}
