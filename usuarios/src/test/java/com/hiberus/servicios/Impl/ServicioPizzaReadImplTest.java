package com.hiberus.servicios.Impl;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.hiberus.clientes.ClientePizzaRead;
import com.hiberus.dto.PizzaDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

public class ServicioPizzaReadImplTest {

    @Mock
    private ClientePizzaRead clientePizzaRead;
    @InjectMocks
    private ServicioPizzaReadImpl servicioPizzaRead;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        servicioPizzaRead = new ServicioPizzaReadImpl(clientePizzaRead);
    }

    @Test
    void obtenerPizzaExiste() {
        long idPizza = 1L;
        PizzaDto pizzaEsperada = PizzaDto.builder()
                .id(Math.toIntExact(idPizza))
                .nombre("Margarita")
                .build();
        ResponseEntity<PizzaDto> response = ResponseEntity.ok(pizzaEsperada);

        when(clientePizzaRead.obtenerPizzaPorId(idPizza)).thenReturn(response);

        PizzaDto resultado = servicioPizzaRead.obtenerPizza(idPizza);

        assertNotNull(resultado, "La pizza no debe ser nula");
        assertEquals(Math.toIntExact(idPizza), resultado.getId(), "El id de la pizza debe coincidir");
        assertEquals("Margarita", resultado.getNombre(), "El nombre de la pizza debe coincidir");
    }

    /**
    @Test
    void obtenerPizzaError() {
        Long idPizza = 2L;
        PizzaDto pizzaEsperada = PizzaDto.builder().id(Math.toIntExact(idPizza)).nombre("Pizza Margarita").build();

        when(clientePizzaRead.obtenerPizzaPorId(idPizza)).thenThrow(new RuntimeException("Error al obtener pizza"));

        PizzaDto resultado = servicioPizzaRead.obtenerPizza(idPizza);

        assertNotNull(resultado, "La pizza no debe ser nula");
        assertEquals(0, resultado.getId(), "El id de la pizza debe ser 0 en caso de error");
        assertEquals("PIZZA INVISIBLE", resultado.getNombre(), "El nombre debe ser 'PIZZA INVISIBLE' en caso de error");
    }
    **/
}