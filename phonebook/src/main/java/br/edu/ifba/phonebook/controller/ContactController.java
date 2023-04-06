package br.edu.ifba.phonebook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifba.phonebook.dto.ContactDtoRequest;
import br.edu.ifba.phonebook.dto.ContactDtoResponse;
import br.edu.ifba.phonebook.service.ContactService;

@RestController
@RequestMapping(path = "/contacts")
public class ContactController {
    
    @Autowired
    private ContactService service;

    @PostMapping
    public ContactDtoResponse save(@RequestBody ContactDtoRequest contact){
        return service.save(contact);
    }

    @GetMapping
    public List<ContactDtoResponse> findAll(){
        return service.findAll();
    }
}
