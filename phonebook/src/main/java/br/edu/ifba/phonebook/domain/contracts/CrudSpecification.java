package br.edu.ifba.phonebook.domain.contracts;

import java.util.List;

public interface CrudSpecification <T, E> {
    
    T save(E e);
    T update(Long id);
    List<T> findAll();
    T findById(Long id);
    T delete(Long id);
    
}
