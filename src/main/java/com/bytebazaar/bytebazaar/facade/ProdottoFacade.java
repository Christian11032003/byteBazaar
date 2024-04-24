package com.bytebazaar.bytebazaar.facade;

import com.bytebazaar.bytebazaar.dto.request.InsertOrModifyProductRequest;
import com.bytebazaar.bytebazaar.exception.messaggiException.NotFoundException;
import com.bytebazaar.bytebazaar.exception.messaggiException.UnAuthorizedException;
import com.bytebazaar.bytebazaar.model.Prodotto;
import com.bytebazaar.bytebazaar.model.Richiesta;
import com.bytebazaar.bytebazaar.model.Utente;
import com.bytebazaar.bytebazaar.repository.RichiestaRepository;
import com.bytebazaar.bytebazaar.service.definition.ProdottoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProdottoFacade
{
    public final ProdottoService serviceProdotto;

    private final RichiestaRepository richiestaRepo;


    public boolean registraProdotto(Utente u, InsertOrModifyProductRequest request)
    {

        Optional<Richiesta> richiestaOptional = richiestaRepo.findByUtente_Username(u.getUsername());

        if(richiestaOptional.isPresent())
        {

            Richiesta r = richiestaOptional.get();

            List<Prodotto> prodottoList = u.getProdotto();


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
                serviceProdotto.salva(p);
                return true;

            }

            else if (r.getStato().name().contains("ACCETTATO") && !notExistProduct)
            {
                //TODO controllare bene qui
                Prodotto p = serviceProdotto.getByNome(request.getNome());
                serviceProdotto.salva(p);
                return true;

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

    public boolean modificaProdotto(Utente u, InsertOrModifyProductRequest request)
    {


        Prodotto p = serviceProdotto.getByNome(request.getNome());





            // Verifica che l'utente associato al prodotto sia lo stesso dell'utente autenticato
            if (p.getUtente().getIdutente() == u.getIdutente()) {
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

                serviceProdotto.salva(p);

                return true;
            }


            else {
                // L'utente non è autorizzato a modificare questo prodotto
                throw new UnAuthorizedException("Non Autorizzato");
            }
        }

}
