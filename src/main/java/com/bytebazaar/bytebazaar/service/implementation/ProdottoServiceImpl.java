package com.bytebazaar.bytebazaar.service.implementation;

import com.bytebazaar.bytebazaar.dto.request.RegistrationOrModifyProdottoRequest;
import com.bytebazaar.bytebazaar.exception.messaggiException.BadRequestException;
import com.bytebazaar.bytebazaar.exception.messaggiException.NotFoundException;
import com.bytebazaar.bytebazaar.exception.messaggiException.UnAuthorizedException;
import com.bytebazaar.bytebazaar.model.*;
import com.bytebazaar.bytebazaar.repository.ProdottoRepository;
import com.bytebazaar.bytebazaar.repository.RichiestaRepository;
import com.bytebazaar.bytebazaar.repository.UtenteRepository;
import com.bytebazaar.bytebazaar.service.definition.ProdottoService;
import com.bytebazaar.bytebazaar.utils.Util;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdottoServiceImpl implements ProdottoService
{
    @Autowired
    private UtenteRepository utenteRepo;

    @Autowired
    private ProdottoRepository prodottoRepo;

    @Autowired
    private RichiestaRepository richiestaRepo;

    @SneakyThrows
    public boolean registraProdotto(RegistrationOrModifyProdottoRequest request)
    {
        Optional<Utente> utenteOptional = utenteRepo.findByUsernameAndPassword(request.getUsername(), request.getPassword());

        if(utenteOptional.isPresent())
        {

            Utente u = utenteOptional.get();

            if(((Util.roleControl(request.getUsername(),request.getPassword(),Ruolo.VENDITORE) || Util.roleControl(request.getUsername(),request.getPassword(),Ruolo.CLIENTEVENDITORE))))
            {

                Optional<Richiesta> richiestaOptional = richiestaRepo.findByUtente_UsernameAndUtente_Password(request.getUsername(),request.getPassword());

                if(richiestaOptional.isPresent()) {
                    Richiesta r = richiestaOptional.get();

                    List<Prodotto> prodottoList = u.getProdotto();

                    boolean notExistProduct = prodottoList.stream().noneMatch(prodotto -> prodotto.getNome().equalsIgnoreCase(request.getNome()));


                    if (r.getStato().name().contains("ACCETTATO") && notExistProduct) {
                        Prodotto p = new Prodotto();
                        p.setUtente(u);
                        p.setImmagineProdotto(request.getImmagine());
                        p.setNome(request.getNome());
                        p.setDescrizione(request.getDescrizione());
                        p.setPrezzo(request.getPrezzo());
                        p.setQuantita(request.getQuantita());
                        prodottoRepo.save(p);
                        return true;
                    }

                    else if (r.getStato().name().contains("ACCETTATO") && !notExistProduct) {

                        Optional<Prodotto> optionalProdotto = prodottoRepo.findByNome(request.getNome());

                        if (optionalProdotto.isPresent()) {
                            return modificaProdotto(request);
                        }

                        else
                        {
                            throw new NotFoundException("Prodotto non trovato");
                        }

                    }

                    else
                    {
                        throw new UnAuthorizedException("Impossibile effetuare l'operazione, controllare la richiesta se è stata accettata ");
                    }

                }

            }

            else
            {
               throw new UnAuthorizedException("Non autorizzato");
            }

        }

        else
        {
            throw new NotFoundException("Utente non trovato");
        }

        throw new BadRequestException("Errore");
    }
    @SneakyThrows
    public boolean modificaProdotto(RegistrationOrModifyProdottoRequest request)
    {
        Optional<Utente> utenteOptional = utenteRepo.findByUsernameAndPassword(request.getUsername(), request.getPassword());

        if (utenteOptional.isPresent() && (Util.roleControl(request.getUsername(), request.getPassword(), Ruolo.VENDITORE)) || (Util.roleControl(request.getUsername(), request.getPassword(), Ruolo.CLIENTEVENDITORE))) {

            Utente u = utenteOptional.get();

            Optional<Prodotto> prodottoOptional = prodottoRepo.findByNome(request.getNome());

            if (prodottoOptional.isPresent()) {
                Prodotto p = prodottoOptional.get();

                // Verifica che l'utente associato al prodotto sia lo stesso dell'utente autenticato
                if (p.getUtente().equals(u)) {
                    // Modifica solo i campi non nulli nella richiesta
                    if (request.getImmagine() != null) {
                        p.setImmagineProdotto(request.getImmagine());
                    }
                    if (request.getNome() != null) {
                        p.setNome(request.getNome());
                    }
                    if (request.getDescrizione() != null) {
                        p.setDescrizione(request.getDescrizione());
                    }
                    if (request.getPrezzo() > 0) {
                        p.setPrezzo(request.getPrezzo());
                    }
                    if (request.getQuantita() >= 0) {
                        p.setQuantita(request.getQuantita());
                    }

                    prodottoRepo.save(p);

                    return true;
                }

                else {
                    // L'utente non è autorizzato a modificare questo prodotto
                    throw new UnAuthorizedException("Non Autorizzato");
                }
            } else {
                // Prodotto non trovato

                throw new NotFoundException("Prodotto non trovato");
            }
        } else {
            // Utente non autorizzato o ruolo non corretto
            throw new UnAuthorizedException("Non Autorizzato");
        }

    }



}
