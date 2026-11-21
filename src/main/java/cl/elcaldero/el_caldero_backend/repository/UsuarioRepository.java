
package cl.elcaldero.el_caldero_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.elcaldero.el_caldero_backend.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    boolean existsByEmailIgnoreCase(String email);
}
