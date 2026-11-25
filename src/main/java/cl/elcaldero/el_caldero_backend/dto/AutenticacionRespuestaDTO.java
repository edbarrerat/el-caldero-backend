package cl.elcaldero.el_caldero_backend.dto;

import lombok.Data;
import lombok.ToString;

@Data
public class AutenticacionRespuestaDTO {

    @ToString.Exclude
    private String accessToken;

    @ToString.Exclude
    private String refreshToken;

    private String tipoToken = "Bearer";

    private UsuarioRespuestaDTO usuario;
}