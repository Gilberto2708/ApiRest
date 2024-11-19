package com.example.contactlistapi.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.contactlistapi.entity.Contact;

public interface ContactRepository extends CrudRepository<Contact, Integer> {

}
