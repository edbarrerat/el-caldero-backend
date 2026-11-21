package cl.elcaldero.el_caldero_backend.dto;

import java.time.OffsetDateTime;

import lombok.Data;

@Data
public class UsuarioRespuestaDTO {

    private Integer idUsuario;

    private String nombre;

    private String email;

    private Boolean activo;

    private OffsetDateTime fechaRegistro;

    private Integer idRol;

    private Integer idDireccion;
}
