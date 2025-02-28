package com.hiberus.servicios.Impl;

import com.hiberus.dto.PizzaDto;
import com.hiberus.modelos.Pizza;
import com.hiberus.repositorios.RepositorioPizzaWrite;
import com.hiberus.servicios.ServicioPizzaWrite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ServicioPizzaWriteImpl implements ServicioPizzaWrite {

    @Autowired
    RepositorioPizzaWrite repositorioPizzaWrite;

    @Override
    public PizzaDto crearPizza(PizzaDto pizzaDto) {
        Pizza pizza = Pizza.builder()
                .nombre(pizzaDto.getNombre())
                .build();

        Pizza pizzaCreada = repositorioPizzaWrite.save(pizza);

        return PizzaDto.builder()
                .id(pizzaCreada.getId())
                .nombre(pizzaCreada.getNombre())
                .build();
    }

    @Override
    public PizzaDto modificarPizza(Integer idPizza, PizzaDto pizzaDto) {
        Pizza pizza = obtenerPizzaPorId(idPizza);
        pizza.setNombre(pizzaDto.getNombre());
        Pizza pizzaModificada = repositorioPizzaWrite.save(pizza);

        return PizzaDto.builder()
                .id(pizzaModificada.getId())
                .nombre(pizzaModificada.getNombre())
                .build();
    }

    public Pizza obtenerPizzaPorId(Integer id) {
        return repositorioPizzaWrite.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pizza no encontrada con el ID: " + id));
    }

}
