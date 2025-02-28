package com.hiberus.controladores;

import com.hiberus.dto.PizzaDto;
import com.hiberus.servicios.ServicioPizzaWrite;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/pizza-write")
public class ControladorPizzaWrite {

    @Autowired
    ServicioPizzaWrite servicioPizzaWrite;

    @PostMapping(value = "/crearPizza")
    ResponseEntity<PizzaDto> crearPizza(@Valid @RequestBody PizzaDto pizzaDto){
        PizzaDto pizzaDtoCreada = servicioPizzaWrite.crearPizza(pizzaDto);
        return new ResponseEntity<>(pizzaDtoCreada, HttpStatus.OK);
    }

    @PatchMapping(value = "/modificarPizza/{id}")
    ResponseEntity<PizzaDto> modificarPizza(Long id, @Valid @RequestBody PizzaDto pizzaDto){
        PizzaDto pizzaDtoModificada = servicioPizzaWrite.modificarPizza(Math.toIntExact(id), pizzaDto);
        return new ResponseEntity<>(pizzaDtoModificada, HttpStatus.OK);
    }
}
