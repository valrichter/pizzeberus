package com.hiberus.repositorios;

import com.hiberus.modelos.Usuario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RepositorioUsuarioTest {

    @Autowired
    RepositorioUsuario repositorioUsuario;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        repositorioUsuario.deleteAll();
    }

    @Test
    void guardarUsuarioDebePersistirDatos() {
        Usuario usuario = Usuario.builder()
                .nombre("Juan")
                .pizzasFav(Set.of(1, 2))
                .build();

        Usuario guardado = repositorioUsuario.save(usuario);

        assertNotNull(guardado.getId(), "El ID debería generarse automáticamente");
        assertEquals("Juan", guardado.getNombre(), "El nombre debería coincidir");
        assertEquals(Set.of(1, 2), guardado.getPizzasFav(), "Las pizzas favoritas deberían coincidir");
    }

    @Test
    void buscarUsuarioPorIdDebeRetornarElUsuario() {
        Usuario usuario = Usuario.builder()
                .nombre("Ana")
                .pizzasFav(Set.of(3))
                .build();
        Usuario guardado = repositorioUsuario.save(usuario);

        Optional<Usuario> encontrado = repositorioUsuario.findById(guardado.getId());

        assertTrue(encontrado.isPresent(), "El usuario debería existir en la base de datos");
        assertEquals("Ana", encontrado.get().getNombre(), "El nombre debería coincidir");
    }

    @Test
    void eliminarUsuarioDebeRemoverloDeLaBaseDeDatos() {
        Usuario usuario = Usuario.builder()
                .nombre("Carlos")
                .pizzasFav(Set.of(4))
                .build();
        Usuario guardado = repositorioUsuario.save(usuario);

        repositorioUsuario.deleteById(guardado.getId());
        Optional<Usuario> encontrado = repositorioUsuario.findById(guardado.getId());

        assertFalse(encontrado.isPresent(), "El usuario debería haber sido eliminado");
    }

    @Test
    void listarUsuariosDebeRetornarTodosLosUsuarios() {
        Usuario usuario1 = Usuario.builder()
                .nombre("Laura")
                .pizzasFav(Set.of(5))
                .build();
        Usuario usuario2 = Usuario.builder()
                .nombre("Miguel")
                .pizzasFav(Set.of(6))
                .build();
        repositorioUsuario.save(usuario1);
        repositorioUsuario.save(usuario2);

        List<Usuario> usuarios = repositorioUsuario.findAll();

        assertEquals(2, usuarios.size(), "Debería haber dos usuarios en la base de datos");
    }
}
