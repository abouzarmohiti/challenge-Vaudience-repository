package com.Vaudience.challenge.api;


import com.Vaudience.challenge.model.Address;
import com.Vaudience.challenge.model.Contact;
import com.Vaudience.challenge.service.ContactService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@AutoConfigureJsonTesters
@WebMvcTest(ContactController.class)
public class ContactControllerTest {

    @MockBean
    private ContactService contactService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<Contact> jsonContact;

    @Autowired
    private JacksonTester<List<Contact>> jsonContactList;

    @Test
    void postNewContact() throws Exception {
        // given
        Contact contact = new Contact(null, "Jack Ma", LocalDate.of(2011, Month.AUGUST, 18), new Address("Bochum", "90443"));


        doNothing().when(contactService).save(contact);

        // when
        MockHttpServletResponse response = mvc.perform(
                post("/api/contact").contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContact.write(contact).getJson()).header("x-resource-version", "1.1"))
                .andReturn().getResponse();

        // then
        then(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

    }

    @Test
    public void getContactList() throws Exception {
        // given

        Contact contact = new Contact(1L, "Tomas Edison", LocalDate.of(2008, Month.MAY, 8), new Address("Bochum", "90443"));
        Contact contact1 = new Contact(2L, "Elli Janson", LocalDate.of(2004, Month.JUNE, 9), new Address("Bochum", "12345"));
        Contact contact2 = new Contact(3L, "Jack Jacksoon", LocalDate.of(1995, Month.JANUARY, 1), new Address("Jena", "12111"));
        Contact contact3 = new Contact(4L, "Haloo Rebeka", LocalDate.of(2008, Month.AUGUST, 1), new Address("Bochum", "90443"));


        List<Contact> contactList = Arrays.asList(contact, contact1, contact2, contact3);
        given(contactService.findAll())
                .willReturn(contactList);

        // when
        MockHttpServletResponse response = mvc.perform(
                get("/api/contact").header("x-resource-version", "1.1"))
                .andReturn().getResponse();

        // then
        then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        then(response.getContentAsString()).isEqualTo(
                jsonContactList.write(
                        contactList
                ).getJson());
    }
}
