package com.arslan.invitationapp.invitationapp.data.repository;

import com.arslan.invitationapp.invitationapp.data.entity.Guest;
import com.arslan.invitationapp.invitationapp.data.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends CrudRepository<User, Long> {
    public Iterable<Guest> getGuestsById(long id);
}