package br.edu.ifba.phonebook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping
    public ContactDtoResponse save(@RequestBody ContactDtoRequest contact){
        return service.save(contact);
    }

    @Override
    public ContactDtoResponse update(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @GetMapping
    public List<ContactDtoResponse> findAll(){
        return service.findAll();
    }

    @Override
    public ContactDtoResponse findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public ContactDtoResponse delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
}
