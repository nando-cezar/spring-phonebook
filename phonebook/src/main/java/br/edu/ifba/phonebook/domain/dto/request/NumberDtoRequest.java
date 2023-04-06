package br.edu.ifba.phonebook.domain.dto.request;

import br.edu.ifba.phonebook.domain.enums.Category;
import br.edu.ifba.phonebook.entities.Number;

public record NumberDtoRequest(String telephone, Category category) {

    public Number toEntity() {
        return new Number(telephone, category);
    }
}
