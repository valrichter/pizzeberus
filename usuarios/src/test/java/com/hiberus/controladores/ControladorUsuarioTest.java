package com.hiberus.controladores;

import com.hiberus.dto.PizzaDto;
import com.hiberus.dto.UsuarioDto;
import com.hiberus.modelos.Usuario;
import com.hiberus.servicios.ServicioPizzaRead;
import com.hiberus.servicios.ServicioUsuarios;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ControladorUsuario.class)
class ControladorUsuarioTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServicioUsuarios servicioUsuarios;

    @MockBean
    private ServicioPizzaRead servicioPizzaRead;

    @InjectMocks
    private ControladorUsuario controladorUsuario;

    @Test
    void crearUsuario() throws Exception {
        UsuarioDto usuarioDto = UsuarioDto.builder()
                .id(1)
                .nombre("Juan")
                .pizzasFav(Set.of(1, 2))
                .build();
        UsuarioDto postUsuarioDto = UsuarioDto.builder()
                .nombre("Juan")
                .pizzasFav(Set.of(1, 2))
                .build();
        when(servicioUsuarios.crearUsuario(any(UsuarioDto.class))).thenReturn(usuarioDto);

        mockMvc.perform(post("/usuarios/crear") // Corregir el endpoint
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"nombre\": \"Juan\", \"pizzasFav\": [1, 2] }"))
                .andExpect(status().isCreated()); // Validar que se devuelve 201 CREATED
    }

    @Test
    void obtenerUsuario() throws Exception {
        Usuario usuario = new Usuario(1, "Juan", Set.of(1));
        when(servicioUsuarios.buscarPorId(eq(1))).thenReturn(usuario);

        mockMvc.perform(get("/usuarios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Juan"));
    }

    @Test
    void listarUsuarios() throws Exception {
        List<UsuarioDto> usuarios = List.of(
                UsuarioDto.builder().id(1).nombre("Juan").build(),
                UsuarioDto.builder().id(2).nombre("Ana").build()
        );
        when(servicioUsuarios.listarUsuarios()).thenReturn(usuarios);

        mockMvc.perform(get("/usuarios/listar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].nombre").value("Ana"));
    }

    @Test
    void borrarUsuario() throws Exception {
        UsuarioDto usuarioDto = UsuarioDto.builder().id(1).nombre("Juan").build();
        when(servicioUsuarios.borrarUsuario(eq(1))).thenReturn(usuarioDto);

        mockMvc.perform(delete("/usuarios/borrar/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Juan"));
    }

    @Test
    void marcarPizzaFav() throws Exception {
        PizzaDto pizzaDto = PizzaDto.builder().id(1).nombre("Margarita").build();
        when(servicioUsuarios.actualizarPizzasFav(eq(1), eq(2L), eq(true))).thenReturn(pizzaDto);

        mockMvc.perform(put("/usuarios/1/pizzas/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Margarita"));
    }

    /**
    @Test
    void modificarUsuario() throws Exception {
        UsuarioDto usuarioDto = UsuarioDto.builder().id(1).nombre("Juan Actualizado").build();
        when(servicioUsuarios.modificarUsuario(eq(1), any(UsuarioDto.class))).thenReturn(usuarioDto);

        mockMvc.perform(patch("/usuarios/modificar/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombre\": \"Juan Actualizado\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Juan Actualizado"));
    }



    @Test
    void desmarcarPizzaFav() throws Exception {
        PizzaDto pizzaDto = PizzaDto.builder().id(2).nombre("Pepperoni").build();
        when(servicioUsuarios.actualizarPizzasFav(eq(1), eq(2L), eq(false))).thenReturn(pizzaDto);

        mockMvc.perform(delete("/usuarios/1/pizzas/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Pepperoni"));
    }
    **/
}
