package cl.elcaldero.el_caldero_backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.elcaldero.el_caldero_backend.model.Estado;
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
    public List<Estado> listarEstados() {
        return estadoService.listarEstados();
    }

    @PostMapping
    public ResponseEntity<Estado> guardarEstado(@Valid @RequestBody Estado estado) {
        Estado estadoGuardado = estadoService.guardarEstado(estado);

        return ResponseEntity.status(201).body(estadoGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estado> actualizarEstado(@PathVariable Integer id, @Valid @RequestBody Estado estado) {
        Estado estadoActualizado = estadoService.actualizarEstado(id, estado);

        if (estadoActualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(estadoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEstado(@PathVariable Integer id) {
        boolean eliminado = estadoService.eliminarEstado(id);

        if (eliminado) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}