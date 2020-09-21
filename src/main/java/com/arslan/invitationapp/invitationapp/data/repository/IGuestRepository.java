package com.arslan.invitationapp.invitationapp.data.repository;

import com.arslan.invitationapp.invitationapp.data.entity.Guest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGuestRepository extends CrudRepository<Guest, Long> {
}
