package com.hiberus.servicios.Impl;

import com.hiberus.dto.PizzaDto;
import com.hiberus.dto.UsuarioDto;
import com.hiberus.modelos.Usuario;
import com.hiberus.repositorios.RepositorioUsuario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServicioUsuariosImplTest {
    @Mock
    private RepositorioUsuario repositorioUsuario;
    @Mock
    private ServicioPizzaReadImpl servicioPizzaRead;
    @InjectMocks
    private ServicioUsuariosImpl servicioUsuarios;

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
        repositorioUsuario.deleteAll();
    }

    @Test
    void crearUsuario() {
        UsuarioDto usuarioDto = UsuarioDto.builder()
                .nombre("Juan")
                .pizzasFav(Set.of(1, 2))
                .build();
        Usuario usuario = Usuario.builder()
                .nombre(usuarioDto.getNombre())
                .pizzasFav(usuarioDto.getPizzasFav())
                .build();
        Usuario usuarioGuardado = Usuario.builder()
                .id(1)
                .nombre(usuario.getNombre())
                .pizzasFav(usuario.getPizzasFav())
                .build();
        when(repositorioUsuario.save(usuario)).thenReturn(usuarioGuardado);

        UsuarioDto resultado = servicioUsuarios.crearUsuario(usuarioDto);
        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        assertEquals("Juan", resultado.getNombre());
        assertEquals(Set.of(1, 2), resultado.getPizzasFav());

        verify(repositorioUsuario, times(1)).save(usuario);
    }

    @Test
    void buscarPorId() {
        Usuario usuario = Usuario.builder()
                .id(1)
                .nombre("Juan")
                .pizzasFav(Set.of(1, 2))
                .build();
        when(repositorioUsuario.findById(1)).thenReturn(Optional.of(usuario));

        Usuario resultado = servicioUsuarios.buscarPorId(1);
        assertNotNull(resultado, "El usuario debería ser encontrado");
        assertEquals(1, resultado.getId());
        assertEquals(usuario, resultado, "El usuario debería ser 'Juan'");
        assertEquals(Set.of(1, 2), resultado.getPizzasFav(), "Las pizzas favoritas deberían ser las correctas");

        verify(repositorioUsuario, times(1)).findById(1);
    }

    @Test
    void listarUsuarios() {
        Usuario usuario1 = Usuario.builder()
                .id(1)
                .nombre("Juan")
                .pizzasFav(Set.of(1))
                .build();
        Usuario usuario2 = Usuario.builder()
                .id(2)
                .nombre("Carlos")
                .pizzasFav(Set.of(2))
                .build();
        List<Usuario> usuarios = List.of(usuario1, usuario2);
        when(repositorioUsuario.findAll()).thenReturn(usuarios);

        List<UsuarioDto> resultado = servicioUsuarios.listarUsuarios();
        UsuarioDto usuario1Dto = UsuarioDto.builder()
                .id(1)
                .nombre("Juan")
                .pizzasFav(Set.of(1))
                .build();
        UsuarioDto usuario2Dto = UsuarioDto.builder()
                .id(2)
                .nombre("Carlos")
                .pizzasFav(Set.of(2))
                .build();

        assertNotNull(resultado, "La lista de usuarios no debería ser nula");
        assertEquals(2, resultado.size(), "La lista de usuarios debería tener 2 elementos");
        assertTrue(resultado.contains(usuario1Dto), "La lista debería contener a 'Juan'");
        assertTrue(resultado.contains(usuario2Dto), "La lista debería contener a 'Carlos'");

        verify(repositorioUsuario, times(1)).findAll();
    }

    @Test
    void modificarNombreUsuario() {
        Usuario usuarioOriginal = Usuario.builder()
                .id(1)
                .nombre("Juan")
                .pizzasFav(Set.of(1, 2))
                .build();
        when(repositorioUsuario.findById(1)).thenReturn(Optional.ofNullable(usuarioOriginal));

        Usuario usuarioModificado = Usuario.builder()
                .id(1)
                .nombre("Juan Carlos")
                .pizzasFav(Set.of(1, 2))
                .build();
        when(repositorioUsuario.save(any(Usuario.class))).thenAnswer(invocation -> invocation.getArgument(0));

        UsuarioDto usuarioDto = UsuarioDto.builder()
                .nombre("Juan Carlos")
                .pizzasFav(Set.of(1, 2))
                .build();

        UsuarioDto resultado = servicioUsuarios.modificarUsuario(1, usuarioDto);

        assertNotNull(resultado, "El resultado no debería ser nulo");
        assertEquals("Juan Carlos", resultado.getNombre(), "El nombre del usuario debería ser 'Juan Carlos'");
        assertEquals(Set.of(1, 2), resultado.getPizzasFav(), "Las pizzas favoritas deberían ser las correctas");

        verify(repositorioUsuario, times(1)).findById(1);
        verify(repositorioUsuario, times(1)).save(usuarioModificado);
    }

    @Test
    void borrarUsuario() {
        Usuario usuario = Usuario.builder()
                .id(1)
                .nombre("Juan")
                .pizzasFav(Set.of(1))
                .build();
        when(repositorioUsuario.existsById(usuario.getId())).thenReturn(true);
        when(repositorioUsuario.findById(usuario.getId())).thenReturn(Optional.of(usuario));
        doNothing().when(repositorioUsuario).deleteById(usuario.getId());

        servicioUsuarios.borrarUsuario(usuario.getId());
        when(repositorioUsuario.existsById(usuario.getId())).thenReturn(false);
        assertFalse(repositorioUsuario.existsById(usuario.getId()));

        verify(repositorioUsuario, times(1)).deleteById(usuario.getId());
    }

    /**
    @Test
    void agregarPizzaAFavoritas() {
        Usuario usuario = Usuario.builder()
                .id(1)
                .nombre("Juan")
                .pizzasFav(Set.of(1))
                .build();
        when(repositorioUsuario.save(usuario)).thenReturn(usuario);
        when(servicioUsuarios.buscarPorId(1)).thenReturn(usuario);
        when(repositorioUsuario.findById(1)).thenReturn(Optional.of(usuario));
        when(servicioPizzaRead.obtenerPizza(1L)).thenReturn(new PizzaDto(1, "Margarita"));
        when(servicioPizzaRead.obtenerPizza(3L)).thenReturn(new PizzaDto(3, "Pepperoni"));


    }

    @Test
    void quitarPizzaDeFavoritas() {
        Usuario usuario = Usuario.builder()
                .id(1)
                .nombre("Juan")
                .pizzasFav(Set.of(1))
                .build();
        when(repositorioUsuario.findById(1)).thenReturn(Optional.ofNullable(usuario));
        when(servicioUsuarios.buscarPorId(1)).thenReturn(usuario);
        UsuarioDto resultado = servicioUsuarios.actualizarPizzasFav(1, 2L, false);

        // Verificar que la pizza se haya eliminado correctamente
        assertFalse(resultado.getPizzasFav().contains(2L), "La pizza debe ser eliminada de las favoritas");
        verify(repositorioUsuario).save(usuario); // Verificar que se haya llamado a save
    }
    **/

    @Test
    void pizzaNoExistente() {
        assertThrows(RuntimeException.class, () -> {
            servicioUsuarios.actualizarPizzasFav(1, 3L, true);
        }, "Se espera una excepción porque la pizza no existe");
    }

}