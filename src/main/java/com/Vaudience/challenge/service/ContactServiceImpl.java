package com.Vaudience.challenge.service;

import com.Vaudience.challenge.exception.ContactIsExistException;
import com.Vaudience.challenge.model.Contact;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ContactServiceImpl implements ContactService  {
    @Override
    public List<Contact> findAll() {
        return null;
    }

    @Override
    public Optional<Contact> findByAddressPostalCode(String postalCode) {
        return Optional.empty();
    }

    @Override
    public void save(Contact contact) throws ContactIsExistException {

    }

    @Override
    public boolean existsByFullNameAndBirthDate(String fullName, LocalDate birthDate) {
        return false;
    }
}
