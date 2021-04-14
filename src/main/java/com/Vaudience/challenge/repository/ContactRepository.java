package com.Vaudience.challenge.repository;

import com.Vaudience.challenge.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    Optional<Contact> findByAddressPostalCode(String postalCode);

    boolean existsByFullNameAndBirthDate(String fullName, LocalDate birthDate);


}
