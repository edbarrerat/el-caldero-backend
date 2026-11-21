
package cl.elcaldero.el_caldero_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/error").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/productos", "/api/productos/*").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/estados", "/api/estados/*").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/metodos-pago", "/api/metodos-pago/*").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/metodos-envio", "/api/metodos-envio/*").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/usuarios/registro").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/productos").denyAll()
                .requestMatchers(HttpMethod.PUT, "/api/productos/*").denyAll()
                .requestMatchers(HttpMethod.DELETE, "/api/productos/*").denyAll()
                .anyRequest().denyAll()
            )
            .formLogin(form -> form.disable())
            .httpBasic(basic -> basic.disable());

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
