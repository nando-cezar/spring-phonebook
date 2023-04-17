package br.edu.ifba.phonebook.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifba.phonebook.entities.Number;

@Repository
public interface NumberRepository extends JpaRepository<Number, Long> {
    public Optional<Number> findByTelephone(String telephone);
}
