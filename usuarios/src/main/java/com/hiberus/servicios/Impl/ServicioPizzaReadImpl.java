package com.hiberus.servicios.Impl;

import com.hiberus.clientes.ClientePizzaRead;
import com.hiberus.dto.PizzaDto;
import com.hiberus.servicios.ServicioPizzaRead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.stereotype.Service;

@Service("feign-pizza-read")
@AllArgsConstructor
@Slf4j
public class ServicioPizzaReadImpl implements ServicioPizzaRead {
    @Autowired
    private final ClientePizzaRead clientePizzaRead;

    @CircuitBreaker(name = "pizza-read", fallbackMethod = "fallBackObtenerPizzaPorId")
    @Override
    public PizzaDto obtenerPizza(Long idPizza) {
        PizzaDto pizzaDto = clientePizzaRead.obtenerPizzaPorId(idPizza).getBody();;
        return pizzaDto;
    }

    // Integer idPizza, ERROR! -> PORQUE??????. Porque no son del mismo tipo?
    private PizzaDto fallBackObtenerPizzaPorId(Throwable throwable){
        return PizzaDto.builder()
                .id(0)
                .nombre("PIZZA INVISIBLE")
                .build();
    }

}
