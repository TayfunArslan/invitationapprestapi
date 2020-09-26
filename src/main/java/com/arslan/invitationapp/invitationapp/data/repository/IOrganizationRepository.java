package com.arslan.invitationapp.invitationapp.data.repository;

import com.arslan.invitationapp.invitationapp.data.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrganizationRepository extends JpaRepository<Organization, Long> {
}
