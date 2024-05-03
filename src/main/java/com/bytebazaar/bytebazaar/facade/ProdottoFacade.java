package com.bytebazaar.bytebazaar.facade;

import com.bytebazaar.bytebazaar.dto.request.prodotto.InsertOrModifyProductRequestDTO;
import com.bytebazaar.bytebazaar.exception.messaggiException.BadRequestException;
import com.bytebazaar.bytebazaar.exception.messaggiException.NotFoundException;
import com.bytebazaar.bytebazaar.exception.messaggiException.SwitchingProtocolException;
import com.bytebazaar.bytebazaar.exception.messaggiException.UnAuthorizedException;
import com.bytebazaar.bytebazaar.model.*;
import com.bytebazaar.bytebazaar.repository.ProdottoRepository;
import com.bytebazaar.bytebazaar.repository.RichiestaRepository;
import com.bytebazaar.bytebazaar.service.definition.ProdottoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdottoFacade
{
    public final ProdottoService serviceProdotto;

    private final RichiestaRepository richiestaRepo;



    public boolean registraProdotto(Utente u, InsertOrModifyProductRequestDTO request)
    {

        Optional<Richiesta> richiestaOptional = richiestaRepo.findByUtente_Username(u.getUsername());

        if(richiestaOptional.isPresent())
        {

            Richiesta r = richiestaOptional.get();

            List<Prodotto> prodottoList = u.getProdotto();


            boolean existProduct = prodottoList.stream().anyMatch(prodotto -> prodotto.getNome().equalsIgnoreCase(request.getNome()));


            if (r.getStato() == Stato.ACCETTATO && !existProduct)
            {
                Prodotto p = new Prodotto();
                p.setUtente(u);
                p.setImmagineprodotto(request.getImmagine());

                if (request.getNome() != null) {
                    p.setNome(request.getNome());
                }
                else
                {

                    throw new BadRequestException("Nome non può essere uguale null");
                }

                if (request.getDescrizione() != null) {
                    p.setDescrizione(request.getDescrizione());
                }
                else
                {

                    throw new BadRequestException("Descrizione non può essere uguale da null");
                }

                if (request.getPrezzo() > 0) {
                    p.setPrezzo(request.getPrezzo());
                }
                else
                {

                    throw new BadRequestException("Prezzo non può essere minore di 0");
                }
                if (request.getQuantita() > 0) {
                    p.setQuantita(request.getQuantita());
                }
                else
                {

                    throw new BadRequestException("Quantità non può essere minore di 0");
                }
                serviceProdotto.salva(p);
                return true;


            }

            else if (r.getStato() == Stato.ACCETTATO)
            {
                //TODO da capire perchè non ritorna il messaggio
                throw new SwitchingProtocolException("Attenzione! Non puoi modificare qui il prodotto, vai nel header modificaProdotto");

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

    public boolean modificaProdotto(Utente u, InsertOrModifyProductRequestDTO request)
    {

        Prodotto p = serviceProdotto.getByNome(request.getNome());

            // Verifica che l'utente associato al prodotto sia lo stesso dell'utente autenticato
            if (p.getUtente().getId() == u.getId()) {
                // Modifica solo i campi non nulli nella richiesta
                if (request.getImmagine() != null) {
                    p.setImmagineprodotto(request.getImmagine());
                }
                else
                {
                    p.setImmagineprodotto(p.getImmagineprodotto());
                }
                if (request.getNome() != null) {
                    p.setNome(request.getNome());
                }
                else
                {
                    p.setNome(p.getNome());
                }
                if (request.getDescrizione() != null) {
                    p.setDescrizione(request.getDescrizione());
                }
                else
                {
                    p.setDescrizione(p.getDescrizione());
                }
                if (request.getPrezzo() > 0) {
                    p.setPrezzo(request.getPrezzo());
                }
                else
                {
                    throw new BadRequestException("Prezzo non può essere minore di 0");
                }


                if (request.getQuantita() > 0 ) {
                    p.setQuantita(request.getQuantita());
                }
                else
                {
                    throw new BadRequestException("Quantita non può essere minore di 0");
                }

                serviceProdotto.salva(p);

                return true;
            }


            else {
                // L'utente non è autorizzato a modificare questo prodotto
                throw new UnAuthorizedException("Non Autorizzato");
            }
        }

}
