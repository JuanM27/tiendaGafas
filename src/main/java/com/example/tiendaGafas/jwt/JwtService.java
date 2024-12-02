package com.example.tiendaGafas.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Base64;


import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;


@Service
public class JwtService {

    private static final String SECRET_KEY = "aB12cD34eF56gH78iJ90kLmnPqrStUvWxYz123456789012uA37";

    public String getToken(UserDetails usuario) {
        return getToken(new HashMap<>(), usuario);
    }

    private String getToken(Map<String, Object> extraClaims, UserDetails usuario) {
        // Obtener roles del usuario y agregarlos a las claims
        List<String> roles = usuario.getAuthorities().stream()
                                    .map(authority -> authority.getAuthority())
                                    .collect(Collectors.toList());
        
        // Agregar los roles al mapa de claims
        extraClaims.put("roles", roles);

        return Jwts.builder()
            .setClaims(extraClaims)
            .setSubject(usuario.getUsername()) // Establecer el correo como subject del token
            .setIssuedAt(new Date(System.currentTimeMillis())) // Fecha de emisión
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24)) // Expiración (24 minutos)
            .signWith(getKey(), SignatureAlgorithm.HS256) // Firma del token
            .compact();
    }

    private Key getKey() {
        byte[] keyBytes = Base64.getDecoder().decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);    
    }

    public String getCorreoFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String correo = getCorreoFromToken(token);
        return (correo.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Claims getAllClaims(String token){
        return Jwts.parserBuilder()
            .setSigningKey(getKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }
}
