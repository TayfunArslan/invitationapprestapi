package com.arslan.invitationapp.invitationapp.data.repository;

import com.arslan.invitationapp.invitationapp.data.entity.OrganizationCoOwner;
import com.arslan.invitationapp.invitationapp.data.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrganizationCoOwnerRepository extends CrudRepository<OrganizationCoOwner, Long> {
    List<User> findUsersById(long id);
}
