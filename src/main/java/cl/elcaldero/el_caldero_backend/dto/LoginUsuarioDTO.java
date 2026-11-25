package cl.elcaldero.el_caldero_backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

@Data
public class LoginUsuarioDTO {

    @NotBlank(message = "El correo electronico es obligatorio")
    @Email(message = "El correo electronico no es valido")
    private String email;

    @NotBlank(message = "La contrasena es obligatoria")
    @ToString.Exclude
    private String password;
}