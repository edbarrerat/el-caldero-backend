package cl.elcaldero.el_caldero_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.elcaldero.el_caldero_backend.dto.EstadoDTO;
import cl.elcaldero.el_caldero_backend.service.EstadoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/estados")
public class EstadoController {

    private final EstadoService estadoService;

    public EstadoController(EstadoService estadoService) {
        this.estadoService = estadoService;
    }

    @GetMapping
    public List<EstadoDTO> listarEstados() {
        return estadoService.listarEstados();
    }

    @PostMapping
    public ResponseEntity<EstadoDTO> guardarEstado(@Valid @RequestBody EstadoDTO dto) {
        EstadoDTO nuevoEstado = estadoService.guardarEstado(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEstado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstadoDTO> actualizarEstado(@PathVariable Integer id, @Valid @RequestBody EstadoDTO dto) {
        EstadoDTO estadoActualizado = estadoService.actualizarEstado(id, dto);

        if (estadoActualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(estadoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEstado(@PathVariable Integer id) {
        boolean eliminado = estadoService.eliminarEstado(id);

        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
