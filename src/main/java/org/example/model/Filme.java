package org.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Filme {

    @Id
    @GeneratedValue
    private Integer id;
    @NotEmpty
    private String titulo;

    @Column(name = "ds_sinopse", length = 500)
    private String sinopse;

}
