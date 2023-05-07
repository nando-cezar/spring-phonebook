package br.edu.ifba.phonebook.domain.dto.request;

import java.util.List;
import java.util.stream.Collectors;

import br.edu.ifba.phonebook.entities.Contact;
import jakarta.validation.constraints.NotNull;

public record ContactDtoRequest(
        @NotNull
        String name,
        @NotNull
        String email,
        List<NumberDtoRequest> numbers){

    public Contact toEntity(){
        return new Contact(
            name, 
            email,
            numbers.stream().map(NumberDtoRequest::toEntity).collect(Collectors.toList())
        );
    }

}
