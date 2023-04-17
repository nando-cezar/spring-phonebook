package br.edu.ifba.phonebook.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifba.phonebook.domain.dto.request.ContactDtoRequest;
import br.edu.ifba.phonebook.domain.dto.response.ContactDtoResponse;
import br.edu.ifba.phonebook.entities.Contact;
import br.edu.ifba.phonebook.entities.Number;
import br.edu.ifba.phonebook.repository.ContactRepository;
import br.edu.ifba.phonebook.repository.NumberRepository;

@Service
public class ContactService{
    
    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private NumberRepository numberRepository;

    public ContactDtoResponse save(ContactDtoRequest data){
        return this.persist(data.toEntity());
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
        return this.persist(data);
    }

    public void deleteById(Long id) {
        contactRepository.deleteById(id);
    }

    private ContactDtoResponse persist(Contact data){        

        var listNumbers = data.getNumbers()
            .stream()
            .distinct()
            .collect(Collectors.toList());

        var isCreate = data.getId() == null;

        data.getNumbers().clear();

        for (Number record : listNumbers) {
            var searchedNumber = numberRepository.findByTelephone(record.getTelephone());
            var isEmpty = searchedNumber.isEmpty();
            if(isEmpty) data.getNumbers().add(record);
            else if(!isEmpty && !isCreate) data.getNumbers().add(searchedNumber.get());
        }

        return ContactDtoResponse.toDto(contactRepository.save(data));
    }
}
