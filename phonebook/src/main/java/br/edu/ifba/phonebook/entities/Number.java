package br.edu.ifba.phonebook.entities;

import br.edu.ifba.phonebook.domain.enums.Category;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name="numbers")
public class Number {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String telephone;
    @Enumerated(EnumType.STRING)
    private Category category;

    public Number(String telephone, Category category) {
        this(null, telephone, category);
    }
}
