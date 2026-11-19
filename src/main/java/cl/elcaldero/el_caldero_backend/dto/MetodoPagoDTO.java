package cl.elcaldero.el_caldero_backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MetodoPagoDTO {

    private Integer idMetodoPago;

    @NotBlank(message = "El nombre del metodo de pago es obligatorio")
    private String nombre;
}