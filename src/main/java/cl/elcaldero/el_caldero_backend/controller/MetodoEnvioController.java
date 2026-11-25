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

import cl.elcaldero.el_caldero_backend.dto.MetodoEnvioDTO;
import cl.elcaldero.el_caldero_backend.service.MetodoEnvioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/metodos-envio")
public class MetodoEnvioController {

    private final MetodoEnvioService metodoEnvioService;

    public MetodoEnvioController(MetodoEnvioService metodoEnvioService) {
        this.metodoEnvioService = metodoEnvioService;
    }

    @GetMapping
    public List<MetodoEnvioDTO> listarMetodosEnvio() {
        return metodoEnvioService.listarMetodosEnvio();
    }

    @PostMapping
    public ResponseEntity<MetodoEnvioDTO> guardarMetodoEnvio(@Valid @RequestBody MetodoEnvioDTO dto) {
        MetodoEnvioDTO nuevoMetodoEnvio = metodoEnvioService.guardarMetodoEnvio(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoMetodoEnvio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MetodoEnvioDTO> actualizarMetodoEnvio(@PathVariable Integer id, @Valid @RequestBody MetodoEnvioDTO dto) {
        MetodoEnvioDTO metodoEnvioActualizado = metodoEnvioService.actualizarMetodoEnvio(id, dto);

        if (metodoEnvioActualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(metodoEnvioActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMetodoEnvio(@PathVariable Integer id) {
        boolean eliminado = metodoEnvioService.eliminarMetodoEnvio(id);

        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
