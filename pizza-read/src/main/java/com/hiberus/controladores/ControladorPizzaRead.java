package com.hiberus.controladores;

import com.hiberus.dto.PizzaDto;
import com.hiberus.modelos.Pizza;
import com.hiberus.servicios.ServicioPizzaRead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/pizza-read")
public class ControladorPizzaRead {

    @Autowired
    ServicioPizzaRead servicioPizzaRead;

    @GetMapping(value = "/pizza/{id}")
    public ResponseEntity<Pizza> obtenerPizzaPorId(@PathVariable Long id) {
        Pizza pizza = servicioPizzaRead.obtenerPizzaPorId(Math.toIntExact(id));
        if (pizza == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pizza, HttpStatus.OK);
    }

    @GetMapping(value = "/listarPizzas")
    ResponseEntity<List<PizzaDto>> obtenerPizzas(){
        List<PizzaDto> listaPizzas = servicioPizzaRead.obtenerPizzas();
        return new ResponseEntity<>(listaPizzas, HttpStatus.OK);
    }
}
