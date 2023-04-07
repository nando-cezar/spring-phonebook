package br.edu.ifba.phonebook.domain.contracts;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface CrudSpecification <T, E> {
    
    ResponseEntity<T> save(E e);
    ResponseEntity<T> update(Long id, E e);
    List<T> findAll();
    ResponseEntity<T> findById(Long id);
    ResponseEntity<T> delete(Long id);
    
}
