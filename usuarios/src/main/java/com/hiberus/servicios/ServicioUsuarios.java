package com.hiberus.servicios;

import com.hiberus.dto.PizzaDto;
import com.hiberus.dto.UsuarioDto;
import com.hiberus.modelos.Usuario;

import java.util.List;
import java.util.Map;

public interface ServicioUsuarios {
    UsuarioDto crearUsuario(UsuarioDto usuarioDto);
    Usuario buscarPorId(Integer idUsuario);
    List<UsuarioDto> listarUsuarios();
    UsuarioDto modificarUsuario(Integer idUsuario, UsuarioDto usuarioModificado);
    UsuarioDto borrarUsuario(Integer idUsusario);
    PizzaDto actualizarPizzasFav(Integer idUsuario, Long idPizza, boolean esFavorita);
    UsuarioDto obtenerUsuarioYPizzas(Integer idUsuario);
}
