package com.bytebazaar.bytebazaar.security;

import com.bytebazaar.bytebazaar.repository.UtenteRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeanConfigurazioneJwt {

    private final UtenteRepository repo;

    // Costruttore che inietta l'istanza di UtenteRepository.
    public BeanConfigurazioneJwt(UtenteRepository repo) {
        this.repo = repo;
    }

    // Definizione di un bean che restituirà un'istanza di UserDetailsService.
    @Bean
    public UserDetailsService userDetailsService() {
        // Questo bean è utilizzato per recuperare i dettagli dell'utente basandosi sul nome utente.
        // Utilizza l'UtenteRepository per cercare l'utente nel database.
        // Se l'utente non viene trovato, viene generata un'eccezione.
        return username -> repo.findByUsername(username).orElseThrow();
    }

    // Definizione di un bean che restituirà un'istanza di PasswordEncoder.
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Questo bean è utilizzato per codificare le password degli utenti.
        // Utilizza l'implementazione BCryptPasswordEncoder per la codifica sicura delle password.
        return new BCryptPasswordEncoder();
    }

    // Definizione di un bean che restituirà un AuthenticationProvider.
    @Bean
    public AuthenticationProvider authenticationProvider() {
        // Questo bean è utilizzato per configurare l'Autenticazione.
        // Si utilizza DaoAuthenticationProvider, che è un'implementazione di base per l'autenticazione DAO.
        // Si imposta il PasswordEncoder e il UserDetailsService.
        DaoAuthenticationProvider dap = new DaoAuthenticationProvider();
        dap.setPasswordEncoder(passwordEncoder()); // Imposta l'encoder delle password.
        dap.setUserDetailsService(userDetailsService()); // Imposta il servizio di dettagli dell'utente.
        return dap;
    }

    // Definizione di un bean che restituirà un AuthenticationManager.

    /**
     * Definisce un bean che restituirà un'istanza di AuthenticationManager.
     * Questo bean è utilizzato per la gestione dell'autenticazione.
     *
     * @param configure l'oggetto AuthenticationConfiguration utilizzato per la configurazione dell'AuthenticationManager
     * @return un'istanza di AuthenticationManager configurata
     * @throws Exception se si verifica un'eccezione durante la configurazione dell'AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configure) throws Exception {
        // Questo bean è utilizzato per la gestione dell'autenticazione.
        // Viene configurato utilizzando un'istanza di AuthenticationConfiguration.
        return configure.getAuthenticationManager();
    }



}
