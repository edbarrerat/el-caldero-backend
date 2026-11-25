
package cl.elcaldero.el_caldero_backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import cl.elcaldero.el_caldero_backend.dto.LoginUsuarioDTO;
import cl.elcaldero.el_caldero_backend.dto.AutenticacionRespuestaDTO;
import cl.elcaldero.el_caldero_backend.service.AutenticacionService;

@RestController
@RequestMapping("/api/v1/autenticacion")
public class AutenticacionController {

    private final AutenticacionService autenticacionService;

    public AutenticacionController(AutenticacionService autenticacionService) {
        this.autenticacionService = autenticacionService;
    }

    @PostMapping("/login")
    public ResponseEntity<AutenticacionRespuestaDTO> iniciarSesion(
            @Valid @RequestBody LoginUsuarioDTO dto) {

        return ResponseEntity.ok(autenticacionService.iniciarSesion(dto));
    }
}
