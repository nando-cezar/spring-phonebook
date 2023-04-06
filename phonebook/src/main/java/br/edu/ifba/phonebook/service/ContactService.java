package br.edu.ifba.phonebook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
    public ContactDtoResponse save(ContactDtoRequest contact){
        var data = contactRepository.save(contact.toEntity());
        return ContactDtoResponse.toDto(data);
    }
    
    @Override
    public ContactDtoResponse update(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    public List<ContactDtoResponse> findAll(){
        return ContactDtoResponse.toListDto(contactRepository.findAll());
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
