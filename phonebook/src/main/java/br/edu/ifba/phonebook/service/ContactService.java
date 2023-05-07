package br.edu.ifba.phonebook.service;

import java.util.List;

import br.edu.ifba.phonebook.domain.exception.model.ContactNotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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

    /**
     * @param data The body content to be created
     * @return a {@code ResponseEntity} instance
     */
    public ContactDtoResponse save(ContactDtoRequest data){
        var entity = data.toEntity();
        return this.persist(entity);
    }

    /**
     * @param name The param to filter (optional)
     * @param pageable The param to pagination
     * @return a {@code ResponseEntity} instance
     */
    @SneakyThrows
    public List<ContactDtoResponse> find(String name, Pageable pageable){
        
        if(name == null){
            var data = contactRepository.findAll(pageable).toList();
            if(data.isEmpty()) throw new ContactNotFoundException();
            return ContactDtoResponse.toListDto(data);
        }

        var data = contactRepository.findByNameContains(name, pageable);
        if(data.isEmpty()) throw new ContactNotFoundException();
        return ContactDtoResponse.toListDto(data);
    }

    /**
     * @param id The id to be searched
     * @return a {@code ResponseEntity} instance
     */
    @SneakyThrows
    public ContactDtoResponse findById(Long id){
        var data = contactRepository.findById(id).orElseThrow(ContactNotFoundException::new);
        return new ContactDtoResponse(data);
    }

    /**
     * @param id The id to be updated
     * @param data The elements/Body Content to be updated
     * @return a {@code ResponseEntity} instance
     */
    public ContactDtoResponse update(Long id, ContactDtoRequest data) {
        var entity = data.toEntity();
        entity.setId(id);
        return this.persist(entity);
    }

    /**
     * @param id The id to be deleted
     */
    public void deleteById(Long id) {
        contactRepository.deleteById(id);
    }

    private ContactDtoResponse persist(Contact data){        

        var listNumbers = data.getNumbers()
            .stream()
            .distinct().toList();

        var isCreate = data.getId() == null;

        data.getNumbers().clear();

        for (Number record : listNumbers) {
            var searchedNumber = numberRepository.findByTelephone(record.getTelephone());
            var isEmpty = searchedNumber.isEmpty();
            if(isEmpty) data.getNumbers().add(record);
            else if(!isCreate) data.getNumbers().add(searchedNumber.get());
        }

        return ContactDtoResponse.toDto(contactRepository.save(data));
    }
}
