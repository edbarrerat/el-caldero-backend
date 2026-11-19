package cl.elcaldero.el_caldero_backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cl.elcaldero.el_caldero_backend.dto.EstadoDTO;
import cl.elcaldero.el_caldero_backend.model.Estado;
import cl.elcaldero.el_caldero_backend.repository.EstadoRepository;

@Service
public class EstadoService {

    private final EstadoRepository estadoRepository;

    public EstadoService(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    public List<EstadoDTO> listarEstados() {
        List<Estado> estados = estadoRepository.findAll();
        List<EstadoDTO> estadosDTO = new ArrayList<>();

        for (Estado estado : estados) {
            EstadoDTO dto = new EstadoDTO();

            dto.setIdEstado(estado.getIdEstado());
            dto.setNombre(estado.getNombre());

            estadosDTO.add(dto);
        }

        return estadosDTO;
    }

    public EstadoDTO guardarEstado(EstadoDTO dto) {
        Estado estado = new Estado();

        estado.setNombre(dto.getNombre());

        Estado estadoGuardado = estadoRepository.save(estado);

        EstadoDTO respuesta = new EstadoDTO();
        respuesta.setIdEstado(estadoGuardado.getIdEstado());
        respuesta.setNombre(estadoGuardado.getNombre());

        return respuesta;
    }

    public EstadoDTO actualizarEstado(Integer id, EstadoDTO dto) {
        Estado estado = estadoRepository.findById(id).orElse(null);

        if (estado == null) {
            return null;
        }

        estado.setNombre(dto.getNombre());

        Estado estadoActualizado = estadoRepository.save(estado);

        EstadoDTO respuesta = new EstadoDTO();
        respuesta.setIdEstado(estadoActualizado.getIdEstado());
        respuesta.setNombre(estadoActualizado.getNombre());

        return respuesta;
    }

    public boolean eliminarEstado(Integer id) {
        if (!estadoRepository.existsById(id)) {
            return false;
        }

        estadoRepository.deleteById(id);
        return true;
    }
}
