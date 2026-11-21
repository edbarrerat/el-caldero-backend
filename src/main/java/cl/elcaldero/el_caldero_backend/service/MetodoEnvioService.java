package cl.elcaldero.el_caldero_backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cl.elcaldero.el_caldero_backend.dto.MetodoEnvioDTO;
import cl.elcaldero.el_caldero_backend.model.MetodoEnvio;
import cl.elcaldero.el_caldero_backend.repository.MetodoEnvioRepository;

@Service
public class MetodoEnvioService {

    private final MetodoEnvioRepository metodoEnvioRepository;

    public MetodoEnvioService(MetodoEnvioRepository metodoEnvioRepository) {
        this.metodoEnvioRepository = metodoEnvioRepository;
    }

    public List<MetodoEnvioDTO> listarMetodosEnvio() {
        List<MetodoEnvio> metodosEnvio = metodoEnvioRepository.findAll();
        List<MetodoEnvioDTO> metodosEnvioDTO = new ArrayList<>();

        for (MetodoEnvio metodoEnvio : metodosEnvio) {
            MetodoEnvioDTO dto = new MetodoEnvioDTO();

            dto.setIdMetodoEnvio(metodoEnvio.getIdMetodoEnvio());
            dto.setNombre(metodoEnvio.getNombre());

            metodosEnvioDTO.add(dto);
        }

        return metodosEnvioDTO;
    }

    public MetodoEnvioDTO guardarMetodoEnvio(MetodoEnvioDTO dto) {
        MetodoEnvio metodoEnvio = new MetodoEnvio();

        metodoEnvio.setNombre(dto.getNombre());

        MetodoEnvio metodoEnvioGuardado = metodoEnvioRepository.save(metodoEnvio);

        MetodoEnvioDTO respuesta = new MetodoEnvioDTO();
        respuesta.setIdMetodoEnvio(metodoEnvioGuardado.getIdMetodoEnvio());
        respuesta.setNombre(metodoEnvioGuardado.getNombre());

        return respuesta;
    }

    public MetodoEnvioDTO actualizarMetodoEnvio(Integer id, MetodoEnvioDTO dto) {
        MetodoEnvio metodoEnvio = metodoEnvioRepository.findById(id).orElse(null);

        if (metodoEnvio == null) {
            return null;
        }

        metodoEnvio.setNombre(dto.getNombre());

        MetodoEnvio metodoEnvioActualizado = metodoEnvioRepository.save(metodoEnvio);

        MetodoEnvioDTO respuesta = new MetodoEnvioDTO();
        respuesta.setIdMetodoEnvio(metodoEnvioActualizado.getIdMetodoEnvio());
        respuesta.setNombre(metodoEnvioActualizado.getNombre());

        return respuesta;
    }

    public boolean eliminarMetodoEnvio(Integer id) {
        if (!metodoEnvioRepository.existsById(id)) {
            return false;
        }

        metodoEnvioRepository.deleteById(id);
        return true;
    }
}
