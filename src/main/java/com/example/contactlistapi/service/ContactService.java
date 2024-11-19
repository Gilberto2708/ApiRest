package com.example.contactlistapi.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.contactlistapi.entity.Contact;
import com.example.contactlistapi.repository.ContactRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ContactService {

    private final ContactRepository contactRepository;

    public Iterable<Contact> getAllContacts() {
        return contactRepository.findAll();
    }   
    
    public Contact findById(Integer id) {
        return contactRepository.findById(id).orElse(null);
    }

    public Contact createContact(Contact contact) {
        contact.setCreateAt(LocalDateTime.now());
        return contactRepository.save(contact);
    }   

    public Contact updateContact(Integer id, Contact contact) {
     Contact contactToUpdate = findById(id);
     contactToUpdate.setName(contact.getName());    
     contactToUpdate.setEmail(contact.getEmail());    
     return contactRepository.save(contactToUpdate);
    }   
 
    public void deleteContact(Integer id) {
        Contact contactToUpdate = findById(id);
        contactRepository.delete(contactToUpdate);
    }   
}
