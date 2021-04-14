package com.Vaudience.challenge.service;

import com.Vaudience.challenge.exception.ContactIsExistException;
import com.Vaudience.challenge.model.Contact;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ContactService {

    List<Contact> findAll();

    Optional<Contact> findByAddressPostalCode(String postalCode);

    void save(Contact contact) throws ContactIsExistException;

    boolean existsByFullNameAndBirthDate(String fullName, LocalDate birthDate);

}
