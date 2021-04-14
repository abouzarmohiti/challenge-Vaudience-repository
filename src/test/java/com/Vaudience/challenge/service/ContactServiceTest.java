package com.Vaudience.challenge.service;

import com.Vaudience.challenge.exception.ContactIsExistException;
import com.Vaudience.challenge.model.Address;
import com.Vaudience.challenge.model.Contact;
import com.Vaudience.challenge.repository.ContactRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ContactServiceTest {
    private ContactService contactService;

    private Contact contact;
    private Contact contact1;
    private Contact contact2;
    private Contact contact3;

    @Mock
    private ContactRepository contactRepository;

    @BeforeEach
    public void setUp() {
        contactService = new ContactServiceImpl(contactRepository);

        contact = new Contact(1L, "Tomas Edison", LocalDate.of(2008, Month.MAY, 8), new Address("Bochum", "90443"));
        contact1 = new Contact(2L, "Elli Janson", LocalDate.of(2004, Month.JUNE, 9), new Address("Bochum", "12345"));
        contact2 = new Contact(3L, "Jack Jacksoon", LocalDate.of(1995, Month.JANUARY, 1), new Address("Jena", "12111"));
        contact3 = new Contact(4L, "Haloo Rebeka", LocalDate.of(2008, Month.AUGUST, 1), new Address("Bochum", "90443"));
    }

    @Test
    @DisplayName("Fetch all contacts")
    public void fetchAllTest() {
        // given
        List<Contact> contactList = Arrays.asList(contact, contact1, contact2, contact3);
        given(contactRepository.findAll()).willReturn(contactList);

        // when
        List<Contact> contactResultList = contactService.findAll();

        // then
        then(contactResultList).isEqualTo(contactList);

    }

    @Test
    public void fetchByPostalCodeTest() {
        List<Contact> contactList = Arrays.asList(contact, contact3);
        given(contactRepository.findByAddressPostalCode("90443")).willReturn(contactList);

        // when
        List<Contact> contactResultList = contactService.findByAddressPostalCode("90443");

        // then
        then(contactResultList).isEqualTo(contactList);

    }

    @Test
    public void createContactTest() throws ContactIsExistException {
        contactService.save(contact);

        verify(contactRepository).save(contact);
    }

    @Test
    void throwsExceptionWhenFullnameIsEmpty() {
        Contact newContact = new Contact(null, null, LocalDate.of(2008, Month.MAY, 8), new Address("Bochum", "90443"));

        try {
            contactService.save(newContact);
        } catch (Exception expected) {
            assertEquals("BookShelf capacity of 2 is reached. You can't add morebooks.", expected.getMessage());
        }
    }

}
