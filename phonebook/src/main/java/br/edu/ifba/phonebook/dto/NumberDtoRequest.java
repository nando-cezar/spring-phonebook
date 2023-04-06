package br.edu.ifba.phonebook.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.edu.ifba.phonebook.domain.enums.Category;
import br.edu.ifba.phonebook.entities.Number;

public record NumberDtoRequest(String telephone, Category category) {

    public List<Number> toListEntity(List<NumberDtoRequest> numbers) {
        return numbers.stream().map(
            e -> new Number(
                e.telephone(), 
                e.category()
            )).collect(Collectors.toList());
    }

    public List<NumberDtoRequest> toListDto(List<Number> numbers) {
        return numbers.stream().map(
            e -> new NumberDtoRequest(
                e.getTelephone(), 
                e.getCategory()
            )).collect(Collectors.toList());
    }

    public Number toEntity() {
        return new Number(telephone, category);
      }
}
