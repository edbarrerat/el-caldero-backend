
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

import cl.elcaldero.el_caldero_backend.dto.MetodoPagoDTO;
import cl.elcaldero.el_caldero_backend.service.MetodoPagoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/metodos-pago")
public class MetodoPagoController {

    private final MetodoPagoService metodoPagoService;

    public MetodoPagoController(MetodoPagoService metodoPagoService) {
        this.metodoPagoService = metodoPagoService;
    }

    @GetMapping
    public List<MetodoPagoDTO> listarMetodosPago() {
        return metodoPagoService.listarMetodosPago();
    }

    @PostMapping
    public ResponseEntity<MetodoPagoDTO> guardarMetodoPago(@Valid @RequestBody MetodoPagoDTO dto) {
        MetodoPagoDTO nuevoMetodoPago = metodoPagoService.guardarMetodoPago(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoMetodoPago);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MetodoPagoDTO> actualizarMetodoPago(@PathVariable Integer id, @Valid @RequestBody MetodoPagoDTO dto) {
        MetodoPagoDTO metodoPagoActualizado = metodoPagoService.actualizarMetodoPago(id, dto);

        if (metodoPagoActualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(metodoPagoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMetodoPago(@PathVariable Integer id) {
        boolean eliminado = metodoPagoService.eliminarMetodoPago(id);

        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
