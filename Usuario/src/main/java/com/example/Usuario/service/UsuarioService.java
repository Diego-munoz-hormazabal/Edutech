package com.example.Usuario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import com.example.Usuario.model.dto.DireccionDTO;
import com.example.Usuario.model.entitie.Usuario;
import com.example.Usuario.model.request.ActualizarUsuario;
import com.example.Usuario.model.request.AgregarUsuario;
import com.example.Usuario.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private WebClient direccionWebClientConfig;

    // LISTAR TODOS LOS VEHICULOS
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    // OBTENER VEHICULO POR ID
    public Usuario obtenerUsuarioPorID(int idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario).orElse(null);
        if (usuario == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehiculo no encontrado.");
        }
        return usuario;
    }

    // AGREGAR VEHICULO
    public Usuario agregarUsuario(AgregarUsuario nuevoUsuario) {
        DireccionDTO direccionDTO = null;
        try {
            direccionDTO = direccionWebClientConfig.get()
                    .uri("/direccion/{id_direccion}", nuevoUsuario.getIdDireccion())
                    .retrieve()
                    .bodyToMono(DireccionDTO.class)
                    .block();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "direccion de usuario a ingresar no encontrada.");
        }
        if (direccionDTO == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ENDPOINT agregarUsuario no ha encontrado la direccion ingresada(IF).");
        }

        Usuario v = new Usuario();
        v.setEmail(nuevoUsuario.getEmail());
        v.setIdDireccion(nuevoUsuario.getIdDireccion());
        v.setNombre(nuevoUsuario.getNombre());
        v.setPassword(nuevoUsuario.getPassword());
        v.setRol(nuevoUsuario.getRol());
        v.setEstado(nuevoUsuario.getEstado());
        return usuarioRepository.save(v);
    }

    // ACTUALIZAR VEHICULO POR ID
    public Usuario actualizarUsuario(ActualizarUsuario actualizarUsuario, int idUsuario) {
        // validar id_vehiculo
        Usuario usuarioExistente = usuarioRepository.findById(idUsuario).orElse(null);
        if (usuarioExistente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ENDPOINT actualizarVehiculo no ha encontrado el id de vehiculo ingresado.");
        }
        // validar id_marca
        DireccionDTO usuarioDTO = null;
        try {
            usuarioDTO = direccionWebClientConfig.get()
                    .uri("/direccion/{id_direccion}", actualizarUsuario.getIdDireccion())
                    .retrieve()
                    .bodyToMono(DireccionDTO.class)
                    .block();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "direccion de usuario a actualizar no encontrada.");
        }
        if (usuarioDTO == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ENDPOINT actualizarUsuario no ha encontrado la direccion ingresada(IF).");
        }

        usuarioExistente.setEmail(actualizarUsuario.getEmail());
        usuarioExistente.setIdDireccion(actualizarUsuario.getIdDireccion());
        usuarioExistente.setNombre(actualizarUsuario.getNombre());
        usuarioExistente.setPassword(actualizarUsuario.getPassword());
        usuarioExistente.setRol(actualizarUsuario.getRol());
        usuarioExistente.setEstado(actualizarUsuario.getEstado());
        return usuarioRepository.save(usuarioExistente);

    }

    // ELIMINAR VEHICULO POR ID
    public String eliminarUsuario(int idUsuario) {
        if (usuarioRepository.existsById(idUsuario)) {
            usuarioRepository.deleteById(idUsuario);
            return "Usuario eliminado correctamente";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ENDPOINT eliminarUsuario no ha encontrado la id_direccion ingresado(IF).");
        }
    }

}
