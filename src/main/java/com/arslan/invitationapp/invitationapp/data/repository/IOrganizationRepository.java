package com.arslan.invitationapp.invitationapp.data.repository;

import com.arslan.invitationapp.invitationapp.data.entity.Organization;
import org.springframework.data.repository.CrudRepository;

public interface IOrganizationRepository extends CrudRepository<Organization, Long> {
}
