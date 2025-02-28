package com.hiberus.controladores;

import com.hiberus.dto.PizzaDto;
import com.hiberus.dto.UsuarioDto;
import com.hiberus.modelos.Usuario;
import com.hiberus.servicios.ServicioPizzaRead;
import com.hiberus.servicios.ServicioUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/usuarios")
public class ControladorUsuario {

    @Autowired
    ServicioUsuarios servicioUsuarios;
    @Autowired
    ServicioPizzaRead servicioPizzaRead;

    // Reafactoriza UsuarioDTO pide muchos parametro inncesarios
    @PostMapping(value = "/crear")
    public ResponseEntity<UsuarioDto> crearUsuario(@Valid @RequestBody UsuarioDto usuarioDto) {
        UsuarioDto usuarioCreado = servicioUsuarios.crearUsuario(usuarioDto);
        return new ResponseEntity<>(usuarioCreado, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{idUsuario}")
    public ResponseEntity <Usuario> obtenerUsuario(@PathVariable Long idUsuario){
        Usuario usuario = servicioUsuarios.buscarPorId(Math.toIntExact(idUsuario));
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @GetMapping(value = "/listar")
    public ResponseEntity <List<UsuarioDto>> listarUsuarios(){
        List<UsuarioDto> listaUsuariosDto = servicioUsuarios.listarUsuarios();
        return new ResponseEntity<>(listaUsuariosDto, HttpStatus.OK);
    }

    @PatchMapping (value = "/modificar/{idUsuario}")
    public ResponseEntity<UsuarioDto> modificarUsuario(
            @PathVariable Integer id,
            @RequestBody UsuarioDto usuarioDto)
    {
        UsuarioDto usuarioActualizado = servicioUsuarios.modificarUsuario(Math.toIntExact(id), usuarioDto);
        return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK);
    }

    @PutMapping(value = "{idUsuario}/pizzas/{idPizza}")
    public ResponseEntity<PizzaDto> marcarPizzaFav(
            @PathVariable Long idUsuario,
            @PathVariable Long idPizza) {
        PizzaDto usuarioActualizado = servicioUsuarios.actualizarPizzasFav(Math.toIntExact(idUsuario), idPizza, true);
        return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}/pizzas/{idPizza}")
    public ResponseEntity<PizzaDto> desmarcarPizzaFav(
            @PathVariable Long idUsuario,
            @PathVariable Long idPizza) {
        PizzaDto usuarioActualizado = servicioUsuarios.actualizarPizzasFav(Math.toIntExact(idUsuario), idPizza, false);
        return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK);
    }

    @DeleteMapping (value = "/borrar/{idUsuario}")
    public ResponseEntity<UsuarioDto> borrarUsuario(@PathVariable Integer idUsuario) {
        UsuarioDto usuarioDtoEliminado = servicioUsuarios.borrarUsuario(idUsuario);
        return new ResponseEntity<>(usuarioDtoEliminado, HttpStatus.OK);
    }

    @GetMapping(value = "/{idUsuario}/pizzas")
    public ResponseEntity <UsuarioDto> obtenerUsuarioYPizzas(@PathVariable Long idUsuario) {
        UsuarioDto usuarioYPizzas = servicioUsuarios.obtenerUsuarioYPizzas(Math.toIntExact(idUsuario));
        return new ResponseEntity<>(usuarioYPizzas, HttpStatus.OK);
    }
}
