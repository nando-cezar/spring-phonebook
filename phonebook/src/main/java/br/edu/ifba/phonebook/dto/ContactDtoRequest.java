package br.edu.ifba.phonebook.dto;

import java.util.ArrayList;
import java.util.List;

import br.edu.ifba.phonebook.entities.Contact;
import br.edu.ifba.phonebook.entities.Number;

public record ContactDtoRequest(String name, String email, List<NumberDtoRequest> numbers){


    public Contact toEntity(){
        List<Number> n = new ArrayList<>();
        Contact contact = new Contact(name, email);
        numbers.forEach(numeroDto -> n.add(numeroDto.toEntity()));
        contact.setNumbers(n);
        return contact;
    }

}
