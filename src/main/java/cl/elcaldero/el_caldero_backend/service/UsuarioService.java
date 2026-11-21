package cl.elcaldero.el_caldero_backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


import cl.elcaldero.el_caldero_backend.dto.RegistroUsuarioDTO;
import cl.elcaldero.el_caldero_backend.dto.UsuarioRespuestaDTO;
import cl.elcaldero.el_caldero_backend.model.Usuario;
import cl.elcaldero.el_caldero_backend.repository.UsuarioRepository;
import cl.elcaldero.el_caldero_backend.model.Rol;
import cl.elcaldero.el_caldero_backend.repository.RolRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;
    

    public UsuarioService(UsuarioRepository usuarioRepository, RolRepository rolRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UsuarioRespuestaDTO> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioRespuestaDTO> usuariosDTO = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            usuariosDTO.add(convertirARespuesta(usuario));
        }

        return usuariosDTO;
    }

    public UsuarioRespuestaDTO buscarUsuario(Integer id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);

        if (usuario == null) {
            return null;
        }

        return convertirARespuesta(usuario);
    }

    
    public UsuarioRespuestaDTO registrarUsuario(RegistroUsuarioDTO dto) {
        if (usuarioRepository.existsByEmailIgnoreCase(dto.getEmail())) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "El correo electronico ya esta registrado"
            );
        }
        
        Rol rolCliente = rolRepository.findByNombre("CLIENTE");

        if (rolCliente == null) {
            throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "No se encontro el rol CLIENTE"
            );
        }

        Usuario usuario = new Usuario();

        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        usuario.setActivo(true);
        usuario.setIdRol(rolCliente.getIdRol());

        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        return convertirARespuesta(usuarioGuardado);
    }


    private UsuarioRespuestaDTO convertirARespuesta(Usuario usuario) {
        UsuarioRespuestaDTO dto = new UsuarioRespuestaDTO();

        dto.setIdUsuario(usuario.getIdUsuario());
        dto.setNombre(usuario.getNombre());
        dto.setEmail(usuario.getEmail());
        dto.setActivo(usuario.getActivo());
        dto.setFechaRegistro(usuario.getFechaRegistro());
        dto.setIdRol(usuario.getIdRol());
        dto.setIdDireccion(usuario.getIdDireccion());

        return dto;
    }
}
