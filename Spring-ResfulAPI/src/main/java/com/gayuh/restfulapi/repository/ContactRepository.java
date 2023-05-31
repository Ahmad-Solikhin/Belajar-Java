package com.gayuh.restfulapi.repository;

import com.gayuh.restfulapi.entity.Contact;
import com.gayuh.restfulapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String>, JpaSpecificationExecutor {

    public Optional<Contact> findFirstByUserAndId(User user, String id);

}
