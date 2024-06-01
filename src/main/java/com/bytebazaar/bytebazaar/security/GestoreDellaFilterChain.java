package com.bytebazaar.bytebazaar.security;

import com.bytebazaar.bytebazaar.model.Ruolo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class GestoreDellaFilterChain {

    private final FilterJwt filterJwt;
    private final AuthenticationProvider provider;


    // Costruttore per inizializzare i campi con i valori passati come argomenti
    public GestoreDellaFilterChain(FilterJwt filterJwt, AuthenticationProvider provider) {
        this.filterJwt = filterJwt;
        this.provider = provider;
    }

    // Metodo annotato come @Bean, gestito da Spring, per configurare la catena di filtri di sicurezza
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        //String[] optionsPath = {"/admin/**", "/clienteVenditore/**", "/venditore/**", "/cliente/**", "/all/**"};

        // Disabilita la protezione CSRF
        httpSecurity.csrf(AbstractHttpConfigurer::disable)

                // Configura le regole di autorizzazione per le richieste HTTP
                .authorizeHttpRequests(auth->auth
                        //.requestMatchers(HttpMethod.OPTIONS, optionsPath).permitAll()

                        // Le richieste a "/admin/**" richiedono l'autenticazione con il ruolo "ADMIN"
                        .requestMatchers("/superAdmin/**").hasAnyRole(Ruolo.SUPERADMIN.toString())

                        // Le richieste a "/seller/**" richiedono l'autenticazione con il ruolo "VENDITORE"
                        .requestMatchers("/admin/**").hasRole(Ruolo.ADMIN.toString())


                        // Le richieste a "/seller/**" richiedono l'autenticazione con il ruolo "VENDITORE"
                        .requestMatchers("/venditore/**").hasRole(Ruolo.VENDITORE.toString())


                        // Le richieste a "/client/**" richiedono l'autenticazione con il ruolo "CLIENTE"
                        .requestMatchers("/cliente/**").hasRole(Ruolo.CLIENTE.toString())


                        // Le richieste a "/all/**" sono consentite a tutti (nessuna autenticazione richiesta)
                        .requestMatchers("/all/**").permitAll()


                        // Tutte le altre richieste sono consentite a tutti
                        .anyRequest().permitAll()
                )

                // Configura la gestione delle sessioni come "STATELESS" (senza sessioni)
                .sessionManagement(sess->sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Disabilita la configurazione di CORS
                .cors(AbstractHttpConfigurer::disable)

                // Configura il provider di autenticazione personalizzato
                .authenticationProvider(provider)

                // Aggiunge il filtro FilterJwt prima del filtro UsernamePasswordAuthenticationFilter
                .addFilterBefore(filterJwt, UsernamePasswordAuthenticationFilter.class);






        // Restituisce l'oggetto httpSecurity configurato come catena di filtri di sicurezza
        return httpSecurity.build();
    }
}
