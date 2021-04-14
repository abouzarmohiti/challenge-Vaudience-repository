package com.Vaudience.challenge;


import com.Vaudience.challenge.model.Address;
import com.Vaudience.challenge.model.Contact;
import com.Vaudience.challenge.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;

@Component
@RequiredArgsConstructor
public class MyRunner implements CommandLineRunner {

    private final ContactRepository contactRepository;

    @Override
    public void run(String... args) throws Exception {
        contactRepository.save(new Contact( null,"Tomas Edison",LocalDate.of(2006, Month.MAY, 23), new Address("Bochum", "90443")));
        contactRepository.save(new Contact(null, "Reza Razavi",LocalDate.of(2000, Month.MARCH, 9), new Address("Bochum", "00483")));
        contactRepository.save(new Contact(null, "Eric Maoo",LocalDate.of(2012, Month.MAY, 18), new Address("Berlin", "90443")));
        contactRepository.save(new Contact(null, "Walter Ahmad",LocalDate.of(1998, Month.DECEMBER, 4), new Address("Munich", "12345")));

    }
}
