package com.bytebazaar.bytebazaar.security;


import com.bytebazaar.bytebazaar.model.Utente;
import com.bytebazaar.bytebazaar.repository.UtenteRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.SecretKey;

import java.util.Date;

@Service
@Data
public class TokenUtil {

    private final UtenteRepository service;

    @Value("${miocodice.jwt.key}")
    private String key;

    public TokenUtil(UtenteRepository service) {
        this.service = service;
    }

    // Metodo per generare una chiave segreta basata sulla chiave JWT specificata
    private SecretKey generaChiave() {
        return Keys.hmacShaKeyFor(key.getBytes());
    }

    // Metodo per generare un token JWT basato sulle informazioni dell'utente
    public String generaToken(Utente u) {
        String ruolo = u.getRuolo().toString();
        String username = u.getUsername();

        // Durata del token impostata a 60 giorni in millisecondi
        long millisecondiDiDurata = 1000L * 60 * 60 * 24 * 60;
        //costruisco il mio token
        return Jwts.builder().claims()
                .add("ruolo", ruolo)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + millisecondiDiDurata))
                .and()
                .signWith(generaChiave())
                .compact();
    }

    // Metodo per ottenere le informazioni (claims) dal token JWT
    private Claims prendiClaimsDalToken(String token) {
        JwtParser parser = Jwts.parser()
                .verifyWith(generaChiave())
                .build();
        return parser.parseSignedClaims(token).getPayload();
    }

    // Metodo per ottenere il soggetto (solitamente il nome utente) dal token JWT
    public String getSubject(String token) {
        return prendiClaimsDalToken(token).getSubject();
    }

    // Metodo per ottenere un oggetto Utente dal token JWT
    public Utente getUtenteFromToken(String token) {
        String username = getSubject(token);
        return service.findByUsername(username).orElseThrow(()->new ResponseStatusException(HttpStatusCode.valueOf(403),"nessun utente con queste credenziali"));
    }









}

