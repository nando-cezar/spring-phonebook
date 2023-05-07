package br.edu.ifba.phonebook.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifba.phonebook.entities.Number;
import org.springframework.stereotype.Repository;

@Repository
public interface NumberRepository extends JpaRepository<Number, Long> {
    Optional<Number> findByTelephone(String telephone);
}
