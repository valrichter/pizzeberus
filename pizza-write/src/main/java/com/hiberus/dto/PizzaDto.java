package com.hiberus.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PizzaDto {
    private Integer id;
    @NotEmpty
    private String nombre;
}
