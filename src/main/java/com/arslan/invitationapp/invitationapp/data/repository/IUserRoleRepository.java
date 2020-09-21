package com.arslan.invitationapp.invitationapp.data.repository;

import com.arslan.invitationapp.invitationapp.data.entity.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRoleRepository extends CrudRepository<UserRole, Long> {
}
