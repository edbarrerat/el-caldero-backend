package cl.elcaldero.el_caldero_backend.service;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import cl.elcaldero.el_caldero_backend.dto.LoginUsuarioDTO;
import cl.elcaldero.el_caldero_backend.dto.AutenticacionRespuestaDTO;
import cl.elcaldero.el_caldero_backend.dto.UsuarioRespuestaDTO;
import cl.elcaldero.el_caldero_backend.model.Usuario;
import cl.elcaldero.el_caldero_backend.repository.UsuarioRepository;

@Service
public class AutenticacionService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UsuarioService usuarioService;

    public AutenticacionService(UsuarioRepository usuarioRepository,
                                PasswordEncoder passwordEncoder,
                                JwtService jwtService,
                                UsuarioService usuarioService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.usuarioService = usuarioService;
    }

    public AutenticacionRespuestaDTO iniciarSesion(LoginUsuarioDTO dto) {

        Usuario usuario = usuarioRepository.findByEmailIgnoreCase(dto.getEmail());

        if (usuario == null) {
            throw new ResponseStatusException(
                HttpStatus.UNAUTHORIZED, "Credenciales incorrectas"
            );
        }

        if (!passwordEncoder.matches(dto.getPassword(), usuario.getPassword())) {
            throw new ResponseStatusException(
                HttpStatus.UNAUTHORIZED, "Credenciales incorrectas"
            );
        }

        if (!Boolean.TRUE.equals(usuario.getActivo())) {
            throw new ResponseStatusException(
                HttpStatus.UNAUTHORIZED, "Credenciales incorrectas"
            );
        }

        String accessToken = jwtService.generarToken(usuario);

        UsuarioRespuestaDTO usuarioDTO = usuarioService.buscarUsuario(usuario.getIdUsuario());

        AutenticacionRespuestaDTO respuesta = new AutenticacionRespuestaDTO();

        respuesta.setAccessToken(accessToken);
        respuesta.setUsuario(usuarioDTO);

        return respuesta;
    }
}