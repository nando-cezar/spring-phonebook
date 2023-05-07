package br.edu.ifba.phonebook.domain.dto.request;

import br.edu.ifba.phonebook.domain.enums.Category;
import br.edu.ifba.phonebook.entities.Number;
import jakarta.validation.constraints.NotNull;

public record NumberDtoRequest(
        @NotNull
        String telephone,
        @NotNull
        Category category) {

    public Number toEntity() {
        return new Number(telephone, category);
    }
}
