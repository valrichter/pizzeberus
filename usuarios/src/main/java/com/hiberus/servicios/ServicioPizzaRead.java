package com.hiberus.servicios;

import com.hiberus.dto.PizzaDto;

public interface ServicioPizzaRead {
    PizzaDto obtenerPizza(Long idPizza);
}
