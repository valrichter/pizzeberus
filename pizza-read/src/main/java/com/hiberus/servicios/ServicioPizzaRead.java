package com.hiberus.servicios;

import com.hiberus.dto.PizzaDto;
import com.hiberus.modelos.Pizza;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ServicioPizzaRead {
    Pizza obtenerPizzaPorId(Integer idPizza);
    List<PizzaDto> obtenerPizzas();
}
