package com.Vaudience.challenge.service;


import com.Vaudience.challenge.exception.ContactIsExistException;
import com.Vaudience.challenge.model.Contact;
import com.Vaudience.challenge.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    @Override
    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    @Override
    public List<Contact> findByAddressPostalCode(String postalCode) {
        return contactRepository.findByAddressPostalCode(postalCode);
    }

    @Override
    public void save(Contact contact) throws ContactIsExistException {
        boolean contactIsExist = contactRepository.existsByFullNameAndBirthDate(contact.getFullName(),
                contact.getBirthDate());
        if (contactIsExist) throw new ContactIsExistException("Contact is exist before");
        contactRepository.save(contact);

    }

    @Override
    public boolean existsByFullNameAndBirthDate(String fullName, LocalDate birthDate) {
        return contactRepository.existsByFullNameAndBirthDate(fullName, birthDate);
    }


}
