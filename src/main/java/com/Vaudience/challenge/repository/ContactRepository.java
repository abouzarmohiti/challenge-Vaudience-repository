package com.Vaudience.challenge.repository;

import com.Vaudience.challenge.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
