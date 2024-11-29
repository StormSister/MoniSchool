package com.monika.kindergarden.repository;

import com.monika.kindergarden.model.Contact;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ContactRepository extends CrudRepository<Contact, Integer> {
    List<Contact> findAllByStatus(String status);

}
