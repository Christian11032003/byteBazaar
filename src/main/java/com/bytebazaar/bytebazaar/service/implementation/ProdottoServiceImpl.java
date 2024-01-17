package com.bytebazaar.bytebazaar.service.implementation;

import com.bytebazaar.bytebazaar.dto.request.RegistrationOrModifyProdottoRequest;

import com.bytebazaar.bytebazaar.exception.messaggiException.NotFoundException;
import com.bytebazaar.bytebazaar.exception.messaggiException.UnAuthorizedException;
import com.bytebazaar.bytebazaar.model.*;
import com.bytebazaar.bytebazaar.repository.ProdottoRepository;
import com.bytebazaar.bytebazaar.repository.RichiestaRepository;
import com.bytebazaar.bytebazaar.service.definition.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProdottoServiceImpl implements ProdottoService
{

    @Autowired
    private ProdottoRepository prodottoRepo;

    @Autowired
    private RichiestaRepository richiestaRepo;


    public boolean registraProdotto(Utente u, RegistrationOrModifyProdottoRequest request)
    {

        Optional<Richiesta> richiestaOptional = richiestaRepo.findByUtente_Username(u.getUsername());

        if(richiestaOptional.isPresent())
        {

            Richiesta r = richiestaOptional.get();
            System.out.println("Prima della lista");
            List<Prodotto> prodottoList = u.getProdotto();  //questo potrebbe essere un problema
            System.out.println("Prima dopo la lista");

            boolean notExistProduct = prodottoList.stream().noneMatch(prodotto -> prodotto.getNome().equalsIgnoreCase(request.getNome()));


            if (r.getStato().name().contains("ACCETTATO") && notExistProduct)
            {
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

            else if (r.getStato().name().contains("ACCETTATO") && !notExistProduct)
            {

                Optional<Prodotto> optionalProdotto = prodottoRepo.findByNome(request.getNome());

                if (optionalProdotto.isPresent())
                {
                    return modificaProdotto(u,request);
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

        else
        {
            throw new NotFoundException("Utente non trovato");
        }

    }

    public boolean modificaProdotto(Utente u, RegistrationOrModifyProdottoRequest request)
    {


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
        }

        else
        {

            throw new NotFoundException("Prodotto non trovato");
        }

    }





}
