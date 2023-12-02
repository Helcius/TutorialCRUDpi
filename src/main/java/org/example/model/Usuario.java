package org.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Usuario {
    @Id
    @GeneratedValue
    private Integer id;
    @NotEmpty
    private String nome;
    private String email;
    @ManyToOne
    private Filme filmeFavorito;
}
