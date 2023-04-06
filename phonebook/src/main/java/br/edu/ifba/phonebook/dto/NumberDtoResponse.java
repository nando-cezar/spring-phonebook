package br.edu.ifba.phonebook.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.edu.ifba.phonebook.entities.Number;
import br.edu.ifba.phonebook.domain.enums.Category;

public record NumberDtoResponse(Long id, String telephone, Category category) {

    public NumberDtoResponse(Number number){
        this(number.getId(), number.getTelephone(), number.getCategory());
    }

    public NumberDtoResponse toDto(Number number) {
        return new NumberDtoResponse(number);
    }

    public static List<NumberDtoResponse> toListDto(List<Number> list){
        return list.stream().map(NumberDtoResponse::new).collect(Collectors.toList());
    }

}
