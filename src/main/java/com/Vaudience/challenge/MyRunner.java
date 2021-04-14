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
        contactRepository.save(new Contact(null, "Toni Wagner", LocalDate.of(2006, Month.MAY, 23), new Address("Würzburg", "90443")));
        contactRepository.save(new Contact(null, "Christian Spruch", LocalDate.of(2000, Month.MARCH, 9), new Address("Würzburg", "00483")));
        contactRepository.save(new Contact(null, "Frederick Rudeck", LocalDate.of(2012, Month.MAY, 18), new Address("Würzburg", "90443")));
        contactRepository.save(new Contact(null, "Abazar Mohiti", LocalDate.of(1991, Month.DECEMBER, 4), new Address("Bochum", "12345")));
    }
}
