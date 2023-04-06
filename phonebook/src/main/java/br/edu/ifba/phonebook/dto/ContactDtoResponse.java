package br.edu.ifba.phonebook.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.edu.ifba.phonebook.entities.Contact;

public record ContactDtoResponse(Long id, String name, String email, List<NumberDtoResponse> numbers){

    public ContactDtoResponse(Contact contact){
        this(
            contact.getId(), 
            contact.getName(), 
            contact.getEmail(),
            NumberDtoResponse.toListDto(contact.getNumbers()) 
        );
    }

    public static ContactDtoResponse toDto(Contact contact) {
        return new ContactDtoResponse(contact);
    }

    public static List<ContactDtoResponse> toListDto(List<Contact> list){
        return list.stream()
        .map(e -> new ContactDtoResponse(
            e.getId(), 
            e.getName(), 
            e.getEmail(),
            NumberDtoResponse.toListDto(e.getNumbers())
            )
        ).collect(Collectors.toList());
    }

}
