package br.edu.ifba.phonebook.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifba.phonebook.domain.dto.request.ContactDtoRequest;
import br.edu.ifba.phonebook.domain.dto.response.ContactDtoResponse;
import br.edu.ifba.phonebook.repository.ContactRepository;

@Service
public class ContactService{
    
    @Autowired
    private ContactRepository contactRepository;

    public ContactDtoResponse save(ContactDtoRequest contact){
        var data = contactRepository.save(contact.toEntity());
        return ContactDtoResponse.toDto(data);
    }

    public Optional<List<ContactDtoResponse>> find(String name){
        
        if(name == null){
            var data = ContactDtoResponse.toListDto(contactRepository.findAll());
            return Optional.of(data);
        }

        var data = ContactDtoResponse.toListDto(contactRepository.findByNameContains(name));
        return Optional.of(data);
    }

    public Optional<ContactDtoResponse> findById(Long id){
        return contactRepository.findById(id).map(ContactDtoResponse::new);
    }

    public ContactDtoResponse update(Long id, ContactDtoRequest entity) {
        var data = entity.toEntity();
        data.setId(id);
        return ContactDtoResponse.toDto(contactRepository.save(data));
    }

    public void deleteById(Long id) {
        contactRepository.deleteById(id);
    }

}
