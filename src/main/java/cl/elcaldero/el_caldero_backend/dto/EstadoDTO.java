package cl.elcaldero.el_caldero_backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoDTO {

    private Integer idEstado;

    @NotBlank(message = "El nombre del estado es obligatorio")
    private String nombre;
}