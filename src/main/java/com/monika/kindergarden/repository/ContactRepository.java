package com.monika.kindergarden.repository;

import com.monika.kindergarden.model.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {
    List<Contact> findAllByStatus(String status);

    Page<Contact> findByStatus(String status, Pageable pageable);

}
