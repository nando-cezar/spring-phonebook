package br.edu.ifba.phonebook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifba.phonebook.entities.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    public List<Contact> findByNameContains(String name);
}
