package br.edu.ifba.phonebook.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifba.phonebook.entities.Number;

public interface NumberRepository extends JpaRepository<Number, Long> {
    public Optional<Number> findByTelephone(String telephone);
}
