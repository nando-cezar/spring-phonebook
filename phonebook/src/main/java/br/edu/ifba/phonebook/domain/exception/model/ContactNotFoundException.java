package br.edu.ifba.phonebook.domain.exception.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ContactNotFoundException extends Exception{
    private String name;

    @Override
    public String getMessage() {
        if(name != null) return "Contact '" + name + "' not found";
        return "Contact not found";
    }
}
