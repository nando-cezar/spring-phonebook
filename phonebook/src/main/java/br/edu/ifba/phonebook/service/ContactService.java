package br.edu.ifba.phonebook.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.edu.ifba.phonebook.domain.contracts.CrudSpecification;
import br.edu.ifba.phonebook.domain.dto.request.ContactDtoRequest;
import br.edu.ifba.phonebook.domain.dto.response.ContactDtoResponse;
import br.edu.ifba.phonebook.repository.ContactRepository;

@Service
public class ContactService implements CrudSpecification <ContactDtoResponse, ContactDtoRequest>{
    
    @Autowired
    private ContactRepository contactRepository;

    @Override
    public ResponseEntity<ContactDtoResponse> save(ContactDtoRequest contact){
        var data = contactRepository.save(contact.toEntity());
        return new ResponseEntity<ContactDtoResponse>(ContactDtoResponse.toDto(data), HttpStatus.OK);
    }
    
    @Override
    public ResponseEntity<ContactDtoResponse> update(Long id, ContactDtoRequest contact) {

        return contactRepository.findById(id)
        .map(record -> {
            record.setName(contact.name());
            record.setEmail(contact.email());
            contactRepository.save(record);
            return ResponseEntity.ok().body(ContactDtoResponse.toDto(record));
        }).orElse(ResponseEntity.notFound().build());
    }

    public List<ContactDtoResponse> findAll(){
        return ContactDtoResponse.toListDto(contactRepository.findAll());
    }

    
    @Override
    public ResponseEntity<ContactDtoResponse> findById(Long id){
        return contactRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(ContactDtoResponse.toDto(record)))
                .orElse(ResponseEntity.notFound().build());
     }
    
    @Override
    public ResponseEntity<ContactDtoResponse> delete(Long id) {
        return contactRepository.findById(id)
           .map(record -> {
            contactRepository.deleteById(id);
               return ResponseEntity.ok().body(ContactDtoResponse.toDto(record));
           }).orElse(ResponseEntity.notFound().build());
    }

}
