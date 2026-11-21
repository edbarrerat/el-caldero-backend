package cl.elcaldero.el_caldero_backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data 
public class EstadoDTO {

    private Integer idEstado;

    @NotBlank(message = "El nombre del estado es obligatorio")
    private String nombre;
}