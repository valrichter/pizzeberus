package com.hiberus.servicios;

import com.hiberus.dto.PizzaDto;
import org.springframework.stereotype.Service;

@Service
public interface ServicioPizzaWrite {
    PizzaDto crearPizza(PizzaDto pizzaDto);
    PizzaDto modificarPizza(Integer idPizza, PizzaDto pizzaDto);
}
