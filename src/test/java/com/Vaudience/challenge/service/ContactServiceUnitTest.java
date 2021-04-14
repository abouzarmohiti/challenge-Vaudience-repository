package com.Vaudience.challenge.service;

import com.Vaudience.challenge.exception.ContactIsExistException;
import com.Vaudience.challenge.model.Address;
import com.Vaudience.challenge.model.Contact;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.TransactionSystemException;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

@DisplayName("Contact unit test")
@SpringBootTest
public class ContactServiceUnitTest {

    @Autowired
    ContactService contactService;

    private Contact contact;
    private Contact contact1;
    private Contact contact2;
    private Contact contact3;

    @BeforeEach
    void setUp() {
        contact = new Contact(1L, "Tomas Edison", LocalDate.of(2008, Month.MAY, 8), new Address("Bochum", "90443"));
        contact1 = new Contact(2L, "Elli Janson", LocalDate.of(2004, Month.JUNE, 9), new Address("Bochum", "12345"));
        contact2 = new Contact(3L, "Jack Jacksoon", LocalDate.of(1995, Month.JANUARY, 1), new Address("Jena", "12111"));
        contact3 = new Contact(4L, "Haloo Rebeka", LocalDate.of(2008, Month.AUGUST, 1), new Address("Bochum", "90443"));
        // contactService = new ContactServiceImpl(contactRepository);
    }

    @Test
    @DisplayName("When Valid Contact is as input in save method")
    public void whenValidContact_thenShouldSave() throws ContactIsExistException {
        Contact newContact = new Contact(null, "Abouzar Mohiti", LocalDate.of(2000, Month.AUGUST, 1),
                new Address("Neyshabour", "90443"));
        contactService.save(newContact);

        List<Contact> contacts = contactService.findAll();

        // Assert
        assertEquals(newContact.getFullName(), contacts.get(4).getFullName());

    }

    @Test
    @DisplayName("When invalid Contact is as input in save method")
    public void whenInvalidContact_thenShouldNotSave() {
        Contact newContact = new Contact(null, "Abouzar Mohiti", LocalDate.of(2022, Month.AUGUST, 1),
                new Address("Neyshabour", "90443"));

        try {
            contactService.save(newContact);
            fail(() -> "Should not be able to save contact with birthDate greather than today");
        } catch (Exception e) {
            // TODO: handle exception
            assertTrue(e instanceof TransactionSystemException, () -> "Should throw TransactionSystemException.");
        }

    }

    @Test
    @DisplayName("When exist Contact is as input in save method")
    public void whenExistenceContact_thenShouldNotSave() {
        Contact newExistContact = new Contact(null, "Tomas Edison", LocalDate.of(2006, Month.MAY, 23), new Address("Bochum", "90443"));
        try {
            contactService.save(newExistContact);
            fail(() -> "Should not be able to save exist contact again");
        } catch (Exception e) {
            // TODO: handle exception
            assertTrue(e instanceof ContactIsExistException, () -> "Should throw ContactIsExistException.");
        }
    }
    @Test
    @DisplayName("Conact List Fetch")
    void contactListContain4AtFirst() {
        List<Contact> contactList = contactService.findAll();
        assertEquals(4, contactList.size(), () -> "Contact list should have four contacts.");
    }

    @Test
    @DisplayName("Conact List Fetch by Postal Code")
    void contactListContain2AtFirstWhenFilterByPostalCode() {
        List<Contact> contactList = contactService.findByAddressPostalCode("90443");
        assertEquals(2, contactList.size(), () -> "Contact list should have two contacts with 90443 postal code.");
    }
}
