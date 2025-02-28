package com.hiberus.servicios.Impl;

import com.hiberus.dto.PizzaDto;
import com.hiberus.dto.UsuarioDto;
import com.hiberus.modelos.Usuario;
import com.hiberus.repositorios.RepositorioUsuario;
import com.hiberus.servicios.ServicioPizzaRead;
import com.hiberus.servicios.ServicioUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ServicioUsuariosImpl implements ServicioUsuarios {
    @Autowired
    RepositorioUsuario repositorioUsuario;
    @Autowired
    ServicioPizzaRead servicioPizzaRead;

    public UsuarioDto crearUsuario(UsuarioDto usuarioDto) {
        Usuario usuario = Usuario.builder()
                .nombre(usuarioDto.getNombre())
                .pizzasFav(usuarioDto.getPizzasFav())
                .build();
        Usuario usuarioCreado = repositorioUsuario.save(usuario);

        return UsuarioDto.builder()
                .id(usuarioCreado.getId())
                .nombre(usuarioCreado.getNombre())
                .pizzasFav(usuarioCreado.getPizzasFav())
                .build();
    }

    public Usuario buscarPorId(Integer id) {
        return repositorioUsuario.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado con el ID: " + id));
    }

    public List<UsuarioDto> listarUsuarios() {
        List<Usuario> listaUsuarios = repositorioUsuario.findAll();
        List<UsuarioDto> listaUsuariosDto = new ArrayList<>();
        for(Usuario usuario: listaUsuarios){
            UsuarioDto usuarioDto = UsuarioDto.builder()
                    .id(usuario.getId())
                    .nombre(usuario.getNombre())
                    .pizzasFav(usuario.getPizzasFav())
                    .build();
            listaUsuariosDto.add(usuarioDto);
        }
        return listaUsuariosDto;
    }

    public UsuarioDto modificarUsuario(Integer idUsuario, UsuarioDto usuarioDto) {
        Usuario usuarioExistente = this.buscarPorId(idUsuario);

        usuarioExistente.setNombre(usuarioDto.getNombre());

        Usuario usuarioActualizado = repositorioUsuario.save(usuarioExistente);

        return UsuarioDto.builder()
                .id(usuarioActualizado.getId())
                .nombre(usuarioActualizado.getNombre())
                .pizzasFav(usuarioActualizado.getPizzasFav())
                .build();
    }

    public PizzaDto actualizarPizzasFav(Integer idUsuario, Long idPizza, boolean esFavorita) {
        Usuario usuario = this.buscarPorId(idUsuario);
        PizzaDto pizzaExistente = servicioPizzaRead.obtenerPizza(idPizza);

        if (pizzaExistente.getId() == 0){
            return PizzaDto.builder()
                    .id(pizzaExistente.getId())
                    .nombre(pizzaExistente.getNombre())
                    .build();
        }

        if (esFavorita) {
            usuario.getPizzasFav().add(pizzaExistente.getId());
        } else {
            usuario.getPizzasFav().remove(pizzaExistente.getId());
        }
        repositorioUsuario.save(usuario);

        return PizzaDto.builder()
                .id(usuario.getId())
                .nombre(pizzaExistente.getNombre())
                .build();
    }

    public UsuarioDto borrarUsuario(Integer id) {
        Usuario usuarioEliminado = this.buscarPorId(id);

        // FIXME: Solucion horrible para retornar las pizzas
        usuarioEliminado.getPizzasFav().size();

        repositorioUsuario.deleteById(id);

        return UsuarioDto.builder()
                .id(usuarioEliminado.getId())
                .nombre(usuarioEliminado.getNombre())
                // FIXME: Solucion horrible para retornar las pizzas
                .pizzasFav(new HashSet<>(usuarioEliminado.getPizzasFav()))
                .build();
    }

    public UsuarioDto obtenerUsuarioYPizzas(Integer idUsuario) {
        Usuario usuario = this.buscarPorId(idUsuario);
        UsuarioDto usuarioDto = UsuarioDto.builder()
                .id(usuario.getId())
                .nombre(usuario.getNombre())
                .pizzasFav(usuario.getPizzasFav())
                .build();

        Set<PizzaDto> pizzaDtos = new HashSet<>(Set.of());
        for (Integer idPizza : usuario.getPizzasFav()) {
            PizzaDto pizzaDtoUsuario = servicioPizzaRead.obtenerPizza(Long.valueOf(idPizza));
            pizzaDtos.add(pizzaDtoUsuario);
        }
        usuarioDto.setPizzasCompletas(pizzaDtos);
        return usuarioDto;
    }
}
