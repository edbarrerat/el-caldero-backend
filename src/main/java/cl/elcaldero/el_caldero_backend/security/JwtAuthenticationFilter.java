package cl.elcaldero.el_caldero_backend.security;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import cl.elcaldero.el_caldero_backend.model.Usuario;
import cl.elcaldero.el_caldero_backend.model.Rol;
import cl.elcaldero.el_caldero_backend.repository.UsuarioRepository;
import cl.elcaldero.el_caldero_backend.repository.RolRepository;
import cl.elcaldero.el_caldero_backend.service.JwtService;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;

    public JwtAuthenticationFilter(JwtService jwtService,
                                   UsuarioRepository usuarioRepository,
                                   RolRepository rolRepository) {
        this.jwtService = jwtService;
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String authorization = request.getHeader("Authorization");

        if (authorization == null || !authorization.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authorization.substring(7);

        Integer idUsuario = jwtService.obtenerIdUsuario(token);

        if (idUsuario == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        Usuario usuario = usuarioRepository.findById(idUsuario).orElse(null);

        if (usuario == null || !Boolean.TRUE.equals(usuario.getActivo())) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        Rol rol = rolRepository.findById(usuario.getIdRol()).orElse(null);

        if (rol == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        SimpleGrantedAuthority autoridad =
                new SimpleGrantedAuthority("ROLE_" + rol.getNombre());

        UsernamePasswordAuthenticationToken autenticacion =
                new UsernamePasswordAuthenticationToken(
                        usuario.getEmail(),
                        null,
                        List.of(autoridad)
                );

        SecurityContextHolder.getContext().setAuthentication(autenticacion);

        filterChain.doFilter(request, response);
    }
}
