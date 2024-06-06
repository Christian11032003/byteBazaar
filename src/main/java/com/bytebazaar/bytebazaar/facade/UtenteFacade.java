package com.bytebazaar.bytebazaar.facade;

import com.bytebazaar.bytebazaar.dto.request.*;
import com.bytebazaar.bytebazaar.dto.response.FeedbackResponseDTO;
import com.bytebazaar.bytebazaar.dto.response.MessaggioResponseDTO;
import com.bytebazaar.bytebazaar.dto.response.ProdottoReponseDTO;
import com.bytebazaar.bytebazaar.exception.messaggiException.BadRequestException;
import com.bytebazaar.bytebazaar.exception.messaggiException.NotFoundException;
import com.bytebazaar.bytebazaar.model.*;
import com.bytebazaar.bytebazaar.repository.FeedbackRepository;
import com.bytebazaar.bytebazaar.repository.MessaggioRepository;
import com.bytebazaar.bytebazaar.repository.ProdottoRepository;
import com.bytebazaar.bytebazaar.security.TokenUtil;
import com.bytebazaar.bytebazaar.service.definition.UtenteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UtenteFacade
{

    // Servizio per la gestione degli utenti nel sistema.
    private final UtenteService serviceUtente;

    // Repository per l'accesso ai dati dei prodotti nel database.
    private final ProdottoRepository prodottoRepo;

    // Repository per l'accesso ai dati dei messaggi nel database.
    private final MessaggioRepository messaggioRepo;

    // Repository per l'accesso ai dati dei feedback nel database.
    private final FeedbackRepository feedbackRepo;

    // Fornisce metodi per la gestione delle richieste nel sistema.
    private final RichiestaFacade richiestaFacade;

    // Utilità per la generazione e la gestione dei token di autenticazione.
    private final TokenUtil tokenUtil;



    /**
     * Registra un nuovo utente con ruolo ADMIN nel sistema.
     *
     * @param request Le informazioni del nuovo utente da registrare come ADMIN.
     * @return True se l'utente è stato registrato con successo, altrimenti lancia un'eccezione BadRequest.
     * @throws BadRequestException Se non è possibile registrare un utente con ruolo diverso da ADMIN.
     */
    public boolean registrationAdmin(RegistrationUserRequestDTO request) {
        if (request.getPassword().equals(request.getPasswordRipetuta())) {
            Utente u = new Utente();
            u.setNome(request.getNome());
            u.setCognome(request.getCognome());
            u.setEmail(request.getEmail());
            u.setUsername(request.getUsername());
            u.setPassword(request.getPassword());
            u.setRuolo(request.getRuolo());
            if (request.getRuolo() == Ruolo.ADMIN) {
                serviceUtente.salva(u);
                return true;
            }
        }
        throw new BadRequestException("Puoi solo registrare un utente con ruolo admin ");
    }


    /**
     * Blocca o sblocca un utente con ruolo Admin.
     *
     * @param request La richiesta di blocco o sblocco dell'utente.
     * @return True se l'operazione è stata completata con successo, altrimenti False.
     * @throws NotFoundException Se l'utente Admin non è trovato.
     */
    public boolean bannedOrUnBannedAdmin(BannedOrUnBannedRequestDTO request) {
        // Ottiene l'utente dal servizio Utente
        Utente u = serviceUtente.getById(request.getIdUtente());

        if (u.getRuolo() == Ruolo.ADMIN) {
            u.setBloccato(!u.isBloccato()); // Cambia lo stato di blocco dell'utente
        } else {
            // Se l'utente non è Admin, lancia un'eccezione NotFoundException
            throw new NotFoundException("Utente ADMIN non trovato");
        }

        serviceUtente.salva(u); // Salva l'utente
        return true; // Restituisce true se l'operazione è stata completata con successo
    }

    /**
     * Blocca o sblocca un utente con ruolo Cliente o Venditore.
     *
     * @param request La richiesta di blocco o sblocco dell'utente.
     * @return True se l'operazione è stata completata con successo, altrimenti False.
     * @throws NotFoundException Se l'utente Admin non è trovato.
     */
    public boolean bannedOrUnBannedUser(BannedOrUnBannedRequestDTO request) {
        // Ottiene l'utente dal servizio Utente
        Utente u = serviceUtente.getById(request.getIdUtente());

        if (u.getRuolo() == Ruolo.CLIENTE || u.getRuolo() == Ruolo.VENDITORE) {
            u.setBloccato(!u.isBloccato()); // Cambia lo stato di blocco dell'utente
        } else {
            // Se l'utente non è Admin, lancia un'eccezione NotFoundException
            throw new NotFoundException("Utente ADMIN non trovato");
        }

        serviceUtente.salva(u); // Salva l'utente
        return true; // Restituisce true se l'operazione è stata completata con successo
    }

    /**
     * Restituisce una lista di utenti con ruolo Admin.
     *
     * @return Una lista di utenti con ruolo Admin.
     */
    public List<Utente> findAllAdmin() {
        return serviceUtente.findAllByRuolo(Ruolo.ADMIN);
    }

    /**
     * Restituisce una lista di utenti con ruolo Cliente.
     *
     * @return Una lista di utenti con ruolo Cliente.
     */
    public List<Utente> findAllClienti() {
        return serviceUtente.findAllByRuolo(Ruolo.CLIENTE);
    }

    /**
     * Restituisce una lista di utenti con ruolo Venditore.
     *
     * @return Una lista di utenti con ruolo Venditore.
     */
    public List<Utente> findAllVenditori() {
        return serviceUtente.findAllByRuolo(Ruolo.VENDITORE);
    }

    /**
     * Restituisce una lista di prodotti dell'utente specificato.
     *
     * @param request La richiesta contenente l'ID dell'utente.
     * @return Una lista di prodotti dell'utente.
     */
    public List<Prodotto> findAllProdottiUser(FindThingsRequestDTO request) {
        return prodottoRepo.trovaProdottiUser(request.getIdUtente());
    }

    /**
     * Restituisce una lista di prodotti presenti nel carrello dell'utente specificato.
     *
     * @param request La richiesta contenente l'ID dell'utente.
     * @return Una lista di prodotti nel carrello dell'utente.
     */
    public List<Prodotto> findAllProdottiUserInKart(FindThingsRequestDTO request) {
        return prodottoRepo.getAllProductInKart(request.getIdUtente());
    }

    /**
     * Restituisce una lista di messaggi dell'utente specificato.
     *
     * @param request La richiesta contenente l'ID dell'utente.
     * @return Una lista di messaggi dell'utente.
     */
    public List<Messaggio> findAllMessaggeUser(FindThingsRequestDTO request) {
        return messaggioRepo.findAllByUtente_Id(request.getIdUtente());
    }

    /**
     * Restituisce una lista di feedback dell'utente specificato.
     *
     * @param request La richiesta contenente l'ID dell'utente.
     * @return Una lista di feedback dell'utente.
     */
    public List<Feedback> findAllFeedbackUser(FindThingsRequestDTO request) {
        return feedbackRepo.findAllByOggettocarrello_Carrello_Utente_Id(request.getIdUtente());
    }

    /**
     * Restituisce una lista di messaggi dell'utente specificato nel formato DTO.
     *
     * @param u L'utente di cui trovare i messaggi.
     * @return Una lista di messaggi dell'utente nel formato DTO.
     */
    public List<MessaggioResponseDTO> findMyOwnMessage(Utente u) {
        List<Messaggio> messaggio = messaggioRepo.findAllByUtente(u);
        List<MessaggioResponseDTO> messaggioFiltrato = new ArrayList<>();

        for (Messaggio m : messaggio) {
            MessaggioResponseDTO mDTO = new MessaggioResponseDTO.BuilderMessaggioResponseDTO()
                    .setIdDestinatario(m.getIddestinatario())
                    .setTesto(m.getTesto())
                    .build();

            messaggioFiltrato.add(mDTO);
        }

        return messaggioFiltrato;
    }


    /**
     * Restituisce una lista di prodotti di altri utenti, filtrati in base alla condizione specificata.
     *
     * @param u       L'utente di cui trovare i prodotti di altri utenti.
     * @param request La richiesta contenente la condizione di filtraggio.
     * @return Una lista di prodotti di altri utenti filtrati per condizione.
     */
    public List<ProdottoReponseDTO> findAllOtherProducts(Utente u, FilterProductRequestDTO request) {
        List<ProdottoReponseDTO> prodottoFiltrato = new ArrayList<>();
        List<Prodotto> prodotto = prodottoRepo.findAllByUtenteIsNotAndCondizione(u, request.getCondizione());
        for (Prodotto p : prodotto) {
            ProdottoReponseDTO pDTO = new ProdottoReponseDTO.BuilderProdottoDTO()
                    .setIdVenditore(p.getUtente().getId())
                    .setNome(p.getNome())
                    .setDescrizione(p.getDescrizione())
                    .setQuantita(p.getQuantita())
                    .setPrezzo(p.getPrezzo())
                    .setCondizione(p.getCondizione())
                    .build2();

            prodottoFiltrato.add(pDTO);
        }

        return prodottoFiltrato;
    }

    /**
     * Restituisce una lista di feedback dell'utente specificato nel formato DTO.
     *
     * @param u L'utente di cui trovare i feedback.
     * @return Una lista di feedback dell'utente nel formato DTO.
     */
    public List<FeedbackResponseDTO> findMyOwnFeedback(Utente u) {
        List<FeedbackResponseDTO> feedbackFiltrato = new ArrayList<>();
        List<Feedback> feedbacks = feedbackRepo.findAllByOggettocarrello_Carrello_Utente(u);

        for (Feedback f : feedbacks) {
            FeedbackResponseDTO fDto = new FeedbackResponseDTO.BuilderFeedbackResponseDTO()
                    .setIdProdotto(f.getOggettocarrello().getProdotto().getId())
                    .setDescrizione(f.getDescrizione())
                    .setValutazione(f.getValutazione())
                    .build();

            feedbackFiltrato.add(fDto);
        }

        return feedbackFiltrato;
    }

    /**
     * Restituisce una lista dei prodotti dell'utente specificato nel formato DTO.
     *
     * @param u L'utente di cui trovare i prodotti.
     * @return Una lista dei prodotti dell'utente nel formato DTO.
     */
    public List<ProdottoReponseDTO> findAllHisProducts(Utente u) {
        List<ProdottoReponseDTO> prodottoFiltrato = new ArrayList<>();
        List<Prodotto> prodotto = prodottoRepo.trovaProdottiUser(u.getId());
        for (Prodotto p : prodotto) {
            ProdottoReponseDTO pDTO = new ProdottoReponseDTO.BuilderProdottoDTO()
                    .setNome(p.getNome())
                    .setDescrizione(p.getDescrizione())
                    .setQuantita(p.getQuantita())
                    .setPrezzo(p.getPrezzo())
                    .setCondizione(p.getCondizione())
                    .build();

            prodottoFiltrato.add(pDTO);
        }

        return prodottoFiltrato;
    }

    //funzionalità di tutti

    /**
     * Registra un nuovo utente nel sistema.
     *
     * @param request Le informazioni del nuovo utente da registrare.
     * @return True se l'utente è stato registrato con successo, altrimenti false.
     */
    public boolean registrationUtente(RegistrationUserRequestDTO request) {
        if (request.getPassword().equals(request.getPasswordRipetuta())) {
            Utente u = new Utente();
            u.setNome(request.getNome());
            u.setCognome(request.getCognome());
            u.setEmail(request.getEmail());
            u.setUsername(request.getUsername());
            u.setPassword(request.getPassword());
            u.setRuolo(request.getRuolo());
            serviceUtente.salva(u);

            if (request.getRuolo() == Ruolo.VENDITORE) {
                return richiestaFacade.registrazioneRichiesta(u);
            } else {
                return true;
            }
        }
        return false;
    }


    /**
     * Effettua il login di un utente nel sistema.
     *
     * @param request Le credenziali di login dell'utente.
     * @return L'utente autenticato.
     */
    public Utente login(LoginRequestDTO request) {
        Utente u = serviceUtente.getByUsername(request.getUsername());
        String token = tokenUtil.generaToken(u);
        u.setToken(token);
        serviceUtente.salva(u);
        return u;
    }

    /**
     * Effettua il logout di un utente dal sistema.
     *
     * @param u L'utente che deve effettuare il logout.
     * @return True se il logout è stato effettuato con successo, altrimenti false.
     */
    public boolean logout(Utente u) {
        u.setToken(null);
        serviceUtente.salva(u);
        return true;
    }








}
