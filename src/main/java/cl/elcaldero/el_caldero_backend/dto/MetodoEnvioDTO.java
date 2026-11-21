package cl.elcaldero.el_caldero_backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MetodoEnvioDTO {

    private Integer idMetodoEnvio;

    @NotBlank(message = "El nombre del metodo de envio es obligatorio")
    private String nombre;
}
