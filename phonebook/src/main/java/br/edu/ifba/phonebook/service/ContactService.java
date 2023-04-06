package br.edu.ifba.phonebook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifba.phonebook.dto.ContactDtoRequest;
import br.edu.ifba.phonebook.dto.ContactDtoResponse;
import br.edu.ifba.phonebook.repository.ContactRepository;

@Service
public class ContactService {
    
    @Autowired
    private ContactRepository contactRepository;

    public ContactDtoResponse save(ContactDtoRequest contact){
        var data = contactRepository.save(contact.toEntity());
        return ContactDtoResponse.toDto(data);
    }

    public List<ContactDtoResponse> findAll(){
        return ContactDtoResponse.toListDto(contactRepository.findAll());
    }


}
