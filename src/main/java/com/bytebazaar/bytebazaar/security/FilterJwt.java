package com.bytebazaar.bytebazaar.security;

import com.bytebazaar.bytebazaar.model.Utente;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// Classe che estende OncePerRequestFilter per filtrare le richieste HTTP e gestire l'autenticazione tramite JWT
@Component
public class FilterJwt extends OncePerRequestFilter {

    // Autowired viene utilizzato per l'iniezione delle dipendenze di Spring
    @Autowired
    private TokenUtil tokenUtil; // Un'istanza di TokenUtil necessaria per la gestione dei token JWT

    /**
     * Questo metodo filtra ogni richiesta HTTP per l'autenticazione basata su token JWT.
     * Estrae l'intestazione "Authorization" dalla richiesta, verifica se contiene un token JWT,
     * e se sì, estrae l'utente associato al token. Successivamente, crea un oggetto di
     * autenticazione e lo imposta nel contesto di sicurezza di Spring.
     * Se l'intestazione "Authorization" è assente o non contiene un token JWT valido,
     * la richiesta viene semplicemente passata alla catena di filtri per l'elaborazione successiva.
     *
     * @param request l'oggetto HttpServletRequest che rappresenta la richiesta HTTP
     * @param response l'oggetto HttpServletResponse che rappresenta la risposta HTTP
     * @param filterChain l'oggetto FilterChain per passare la richiesta alla catena di filtri
     * @throws ServletException se si verifica un'eccezione durante il filtraggio della richiesta
     * @throws IOException se si verifica un'eccezione di input/output durante il filtraggio della richiesta
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Estrae l'intestazione "Authorization" dall'oggetto HttpServletRequest.
        String authCode = request.getHeader("Authorization");

        // Controlla se l'intestazione è presente e inizia con "Bearer".
        if (authCode != null && authCode.startsWith("Bearer")) {
            // Se l'intestazione contiene un token "Bearer", estrae il token stesso rimuovendo "Bearer ".
            String token = authCode.substring(7); // Rimuove la parte "Bearer "

            // Utilizza un oggetto "tokenUtil" per ottenere l'utente associato al token.
            Utente u = tokenUtil.getUtenteFromToken(token);
            if(!u.getToken().equals(token)){
                response.setStatus(403);
                return;
            }
            // Crea un oggetto "UsernamePasswordAuthenticationToken" che rappresenta l'autenticazione dell'utente.
            // L'oggetto contiene l'utente (principal), null per le credenziali (poiché è autenticazione basata su token) e le autorizzazioni dell'utente.
            UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(u, null, u.getAuthorities());

            // Imposta i dettagli dell'autenticazione basati sulla richiesta HTTP, ad esempio, l'indirizzo IP del client.
            upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // Imposta l'oggetto di autenticazione appena creato nel contesto di sicurezza di Spring.
            // Questo indica che l'utente è stato autenticato con successo.
            SecurityContextHolder.getContext().setAuthentication(upat);
        }

        // Passa la richiesta all'interno della catena di filtri per l'elaborazione successiva.
        filterChain.doFilter(request, response);
    }

}