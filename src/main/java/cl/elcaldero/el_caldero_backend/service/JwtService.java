package cl.elcaldero.el_caldero_backend.service;

import java.util.Date;
import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import cl.elcaldero.el_caldero_backend.model.Usuario;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String claveSecreta;

    @Value("${jwt.expiration}")
    private long expiracion;

    private SecretKey obtenerClave() {
        return Keys.hmacShaKeyFor(
            Decoders.BASE64.decode(claveSecreta)
        );
    }

    public String generarToken(Usuario usuario) {
        return Jwts.builder()
                .subject(usuario.getEmail())
                .claim("idUsuario", usuario.getIdUsuario())
                .claim("idRol", usuario.getIdRol())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiracion))
                .signWith(obtenerClave(), Jwts.SIG.HS256)
                .compact();
    }

    public Integer obtenerIdUsuario(String token) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(obtenerClave())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            if (claims.getExpiration() == null) {
                return null;
            }

            Integer idUsuario = claims.get("idUsuario", Integer.class);

            if (idUsuario == null || idUsuario <= 0) {
                return null;
            }

            return idUsuario;

        } catch (JwtException | IllegalArgumentException e) {
            return null;
        }
    }
}