package cl.elcaldero.el_caldero_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.elcaldero.el_caldero_backend.model.Estado;
import cl.elcaldero.el_caldero_backend.repository.EstadoRepository;

@Service
public class EstadoService {

    private final EstadoRepository estadoRepository;

    public EstadoService(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    public List<Estado> listarEstados() {
        return estadoRepository.findAll();
    }

    public Estado guardarEstado(Estado estado) {
        return estadoRepository.save(estado);
    }

    public Estado actualizarEstado(Integer id, Estado estado) {
        Estado estadoExistente = estadoRepository.findById(id).orElse(null);

        if (estadoExistente == null) {
            return null;
        }

        estadoExistente.setNombre(estado.getNombre());

        return estadoRepository.save(estadoExistente);
    }

    public boolean eliminarEstado(Integer id) {
        if (estadoRepository.existsById(id)) {
            estadoRepository.deleteById(id);
            return true;
        }

        return false;
    }
}