package cl.elcaldero.el_caldero_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.elcaldero.el_caldero_backend.dto.RegistroUsuarioDTO;
import cl.elcaldero.el_caldero_backend.dto.UsuarioRespuestaDTO;
import cl.elcaldero.el_caldero_backend.service.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public List<UsuarioRespuestaDTO> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioRespuestaDTO> buscarUsuario(@PathVariable Integer id) {
        UsuarioRespuestaDTO usuario = usuarioService.buscarUsuario(id);

        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/registro")
    public ResponseEntity<UsuarioRespuestaDTO> registrarUsuario(@Valid @RequestBody RegistroUsuarioDTO dto) {
        UsuarioRespuestaDTO nuevoUsuario = usuarioService.registrarUsuario(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }
}
