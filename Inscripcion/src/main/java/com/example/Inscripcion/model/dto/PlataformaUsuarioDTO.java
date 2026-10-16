package com.example.Inscripcion.model.dto;

public record PlataformaUsuarioDTO(Integer idUsuario, String nombre, String email, String password, String rol,
                String estado,
                Integer idPlataforma, String nombrePlataforma, String descripcion, String urlPlataforma) {

}
