package com.hiberus.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class UsuarioDto {
    private Integer id;
    @NotBlank(message = "Debe agregar el nombre")
    private String nombre;
    private Set<Integer> pizzasFav;
    private Set<PizzaDto> pizzasCompletas;
}
