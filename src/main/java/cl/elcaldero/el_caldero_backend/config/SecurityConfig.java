package cl.elcaldero.el_caldero_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import cl.elcaldero.el_caldero_backend.security.JwtAuthenticationFilter;
import cl.elcaldero.el_caldero_backend.service.JwtService;
import cl.elcaldero.el_caldero_backend.repository.UsuarioRepository;
import cl.elcaldero.el_caldero_backend.repository.RolRepository;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            JwtService jwtService,
            UsuarioRepository usuarioRepository,
            RolRepository rolRepository) throws Exception {

        JwtAuthenticationFilter jwtAuthenticationFilter =
                new JwtAuthenticationFilter(
                        jwtService,
                        usuarioRepository,
                        rolRepository
                );

        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable())
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint((request, response, authException) ->
                                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED))
                        .accessDeniedHandler((request, response, accessDeniedException) ->
                                response.setStatus(HttpServletResponse.SC_FORBIDDEN))
                )
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers(HttpMethod.POST, "/api/v1/usuarios/registro").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/autenticacion/login").permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/v1/productos", "/api/v1/productos/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/metodos-pago", "/api/v1/metodos-pago/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/metodos-envio", "/api/v1/metodos-envio/*").permitAll()

                        .requestMatchers("/api/v1/productos", "/api/v1/productos/*").hasRole("ADMINISTRADOR")
                        .requestMatchers("/api/v1/estados", "/api/v1/estados/*").hasRole("ADMINISTRADOR")
                        .requestMatchers("/api/v1/metodos-pago", "/api/v1/metodos-pago/*").hasRole("ADMINISTRADOR")
                        .requestMatchers("/api/v1/metodos-envio", "/api/v1/metodos-envio/*").hasRole("ADMINISTRADOR")
                        .requestMatchers("/api/v1/usuarios", "/api/v1/usuarios/*").hasRole("ADMINISTRADOR")

                        .requestMatchers("/error").permitAll()
                        .anyRequest().denyAll()
                )
                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}