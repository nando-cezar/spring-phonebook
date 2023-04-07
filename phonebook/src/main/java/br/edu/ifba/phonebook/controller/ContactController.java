package br.edu.ifba.phonebook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifba.phonebook.domain.contracts.CrudSpecification;
import br.edu.ifba.phonebook.domain.dto.request.ContactDtoRequest;
import br.edu.ifba.phonebook.domain.dto.response.ContactDtoResponse;
import br.edu.ifba.phonebook.service.ContactService;

@RestController
@RequestMapping(path = "/contacts")
public class ContactController implements CrudSpecification <ContactDtoResponse, ContactDtoRequest>{
    
    @Autowired
    private ContactService service;

    @Override
    @PostMapping
    public ResponseEntity<ContactDtoResponse> save(@RequestBody ContactDtoRequest contact){
        return service.save(contact);
    }

    @Override
    @PutMapping(path = "/{id}")
    public ResponseEntity<ContactDtoResponse> update(@PathVariable Long id, @RequestBody ContactDtoRequest contact) {
        return service.update(id, contact);
    }

    @GetMapping
    public List<ContactDtoResponse> findAll(){
        return service.findAll();
    }

    @Override
    @GetMapping(path = "/{id}")
    public ResponseEntity<ContactDtoResponse> findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @Override
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ContactDtoResponse> delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
