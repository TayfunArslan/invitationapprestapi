package com.arslan.invitationapp.invitationapp.data.repository;

import com.arslan.invitationapp.invitationapp.data.entity.Organization;
import com.arslan.invitationapp.invitationapp.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IOrganizationRepository extends JpaRepository<Organization, Long> {
    //    Optional<User> findUserById(long id);
    List<Organization> findOrganizationsByCreatedUserId(long createdUser_id);

    Boolean existsByCode(String code);
}