package br.edu.ifba.phonebook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifba.phonebook.entities.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    public List<Contact> findByNameContains(String name);
}
