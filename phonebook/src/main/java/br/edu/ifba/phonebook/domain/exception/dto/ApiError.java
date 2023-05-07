package br.edu.ifba.phonebook.domain.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ApiError {
    List<String> errors;
}
