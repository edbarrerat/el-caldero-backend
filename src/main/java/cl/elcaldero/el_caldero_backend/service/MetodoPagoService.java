
package cl.elcaldero.el_caldero_backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cl.elcaldero.el_caldero_backend.dto.MetodoPagoDTO;
import cl.elcaldero.el_caldero_backend.model.MetodoPago;
import cl.elcaldero.el_caldero_backend.repository.MetodoPagoRepository;

@Service
public class MetodoPagoService {

    private final MetodoPagoRepository metodoPagoRepository;

    public MetodoPagoService(MetodoPagoRepository metodoPagoRepository) {
        this.metodoPagoRepository = metodoPagoRepository;
    }

    public List<MetodoPagoDTO> listarMetodosPago() {
        List<MetodoPago> metodosPago = metodoPagoRepository.findAll();
        List<MetodoPagoDTO> metodosPagoDTO = new ArrayList<>();

        for (MetodoPago metodoPago : metodosPago) {
            MetodoPagoDTO dto = new MetodoPagoDTO();

            dto.setIdMetodoPago(metodoPago.getIdMetodoPago());
            dto.setNombre(metodoPago.getNombre());

            metodosPagoDTO.add(dto);
        }

        return metodosPagoDTO;
    }

    public MetodoPagoDTO guardarMetodoPago(MetodoPagoDTO dto) {
        MetodoPago metodoPago = new MetodoPago();

        metodoPago.setNombre(dto.getNombre());

        MetodoPago metodoPagoGuardado = metodoPagoRepository.save(metodoPago);

        MetodoPagoDTO respuesta = new MetodoPagoDTO();
        respuesta.setIdMetodoPago(metodoPagoGuardado.getIdMetodoPago());
        respuesta.setNombre(metodoPagoGuardado.getNombre());

        return respuesta;
    }

    public MetodoPagoDTO actualizarMetodoPago(Integer id, MetodoPagoDTO dto) {
        MetodoPago metodoPago = metodoPagoRepository.findById(id).orElse(null);

        if (metodoPago == null) {
            return null;
        }

        metodoPago.setNombre(dto.getNombre());

        MetodoPago metodoPagoActualizado = metodoPagoRepository.save(metodoPago);

        MetodoPagoDTO respuesta = new MetodoPagoDTO();
        respuesta.setIdMetodoPago(metodoPagoActualizado.getIdMetodoPago());
        respuesta.setNombre(metodoPagoActualizado.getNombre());

        return respuesta;
    }

    public boolean eliminarMetodoPago(Integer id) {
        if (!metodoPagoRepository.existsById(id)) {
            return false;
        }

        metodoPagoRepository.deleteById(id);
        return true;
    }
}
