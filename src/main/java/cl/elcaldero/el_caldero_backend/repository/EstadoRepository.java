package cl.elcaldero.el_caldero_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cl.elcaldero.el_caldero_backend.model.Estado;

public interface EstadoRepository extends JpaRepository<Estado, Integer> {

}