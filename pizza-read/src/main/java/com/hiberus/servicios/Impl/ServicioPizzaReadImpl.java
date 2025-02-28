package com.hiberus.servicios.Impl;

import com.hiberus.dto.PizzaDto;
import com.hiberus.modelos.Pizza;
import com.hiberus.repositorios.RepositorioPizzaRead;
import com.hiberus.servicios.ServicioPizzaRead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ServicioPizzaReadImpl implements ServicioPizzaRead {

    @Autowired
    RepositorioPizzaRead repositorioPizzaRead;

    @Override
    public Pizza obtenerPizzaPorId(Integer id) {
        return repositorioPizzaRead.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado con el ID: " + id));
    }

    @Override
    public List<PizzaDto> obtenerPizzas() {
        List<Pizza> pizzas = repositorioPizzaRead.findAll();
        return pizzas.stream()
                .map(pizza -> PizzaDto.builder()
                        .id(pizza.getId())
                        .nombre(pizza.getNombre())
                        .build())
                .collect(Collectors.toList());
    }

}
