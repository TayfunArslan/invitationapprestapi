package com.arslan.invitationapp.invitationapp.data.repository;

import com.arslan.invitationapp.invitationapp.data.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOrganizationRepository extends JpaRepository<Organization, Long> {
    //    Optional<User> findUserById(long id);
    List<Organization> findOrganizationsByCreatedUserId(long createdUser_id);

    Boolean existsByCode(String code);
}