package com.example.contactlistapi;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.contactlistapi.entity.Contact;
import com.example.contactlistapi.repository.ContactRepository;

@SpringBootApplication
public class ContactlistapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContactlistapiApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(ContactRepository contactRepository) {
        return args -> {
            List<Contact> contacts = Arrays.asList(
                new Contact(null, "Cristian", "cristian@.com", LocalDateTime.now()),
                new Contact(null, "Juan", "juan@.com", LocalDateTime.now()),
                new Contact(null, "Jose", "jose@.com", LocalDateTime.now()),
                new Contact(null, "Pedro", "pedro@.com", LocalDateTime.now())
            );
            
            contactRepository.saveAll(contacts);
        };
    }
}