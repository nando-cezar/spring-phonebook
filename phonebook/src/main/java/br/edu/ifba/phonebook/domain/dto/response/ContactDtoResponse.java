package br.edu.ifba.phonebook.domain.dto.response;

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

    public Contact toEntity(){
        return new Contact(id,
            name, 
            email,
            numbers.stream().map(e -> e.toEntity()).collect(Collectors.toList())
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
