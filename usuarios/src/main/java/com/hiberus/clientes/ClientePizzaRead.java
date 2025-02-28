package com.hiberus.clientes;

import com.hiberus.dto.PizzaDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// TODO: fixear "http://localhost:8081/pizza-read", no se porque no reconce el servicio por si solo
// Talvez en la configuracion de Eureka, no lo regitro bien? Ni idea
@FeignClient(name = "pizza-read", url = "http://localhost:8081/pizza-read")
public interface ClientePizzaRead {
    @GetMapping(value = "/pizza/{id}")
    ResponseEntity<PizzaDto> obtenerPizzaPorId(@PathVariable Long id);
}
