package com.bytebazaar.bytebazaar;

import com.bytebazaar.bytebazaar.dto.request.*;
import com.bytebazaar.bytebazaar.model.Condizione;
import com.bytebazaar.bytebazaar.model.Ruolo;
import com.bytebazaar.bytebazaar.model.Stato;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Testing
{
    @Autowired
    private MockMvc mock;


    @Test
    @Order(1)
    @WithUserDetails("chri")
    public void registrazioneAdmin() throws Exception {
        RegistrationUserRequestDTO r=new RegistrationUserRequestDTO();
        r.setNome("john");
        r.setCognome("Elton");
        r.setEmail("john.doe@example.com");
        r.setUsername("john");
        r.setPassword("123");
        r.setPasswordRipetuta("123");
        r.setRuolo(Ruolo.ADMIN);
        ObjectMapper om=new ObjectMapper();
        String json=om.writeValueAsString(r);
        mock.perform(MockMvcRequestBuilders.post("/superAdmin/registrationAdmin")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();



    }




    @Test
    @Order(2)
    public void registrazioneVenditore() throws Exception {
        RegistrationUserRequestDTO r=new RegistrationUserRequestDTO();
        r.setNome("Alessia");
        r.setCognome("Aprile");
        r.setEmail("alessiaaprile@gmail.com");
        r.setUsername("ale");
        r.setPassword("Alessia-2003");
        r.setPasswordRipetuta("Alessia-2003");
        r.setRuolo(Ruolo.VENDITORE);
        ObjectMapper om=new ObjectMapper();
        String json=om.writeValueAsString(r);
        mock.perform(MockMvcRequestBuilders.post("/all/registration")
            .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();



    }

    @Test
    @Order(3)
    public void registrazioneCliente2() throws Exception {
        RegistrationUserRequestDTO r=new RegistrationUserRequestDTO();
        r.setNome("Matteo");
        r.setCognome("Convertino");
        r.setEmail("matteo03@gmail.com");
        r.setUsername("mat");
        r.setPassword("123");
        r.setPasswordRipetuta("123");
        r.setRuolo(Ruolo.CLIENTE);
        ObjectMapper om=new ObjectMapper();
        String json=om.writeValueAsString(r);
        mock.perform(MockMvcRequestBuilders.post("/all/registration")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();

    }

    @Test
    @Order(4)
    @WithUserDetails("chri")
    public void registrazioneAdmin2() throws Exception {
        RegistrationUserRequestDTO r=new RegistrationUserRequestDTO();
        r.setNome("luca");
        r.setCognome("Lagana");
        r.setEmail("luca.doe@example.com");
        r.setUsername("luca");
        r.setPassword("123");
        r.setPasswordRipetuta("123");
        r.setRuolo(Ruolo.ADMIN);
        ObjectMapper om=new ObjectMapper();
        String json=om.writeValueAsString(r);
        mock.perform(MockMvcRequestBuilders.post("/superAdmin/registrationAdmin")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();



    }

    @Test
    @Order(5)
    @WithUserDetails("chri")
    public void bannedOrUnBannedAdmin() throws Exception {
        BannedOrUnBannedRequestDTO b =new BannedOrUnBannedRequestDTO();
        b.setIdUtente(5);
        ObjectMapper om=new ObjectMapper();
        String json=om.writeValueAsString(b);
        mock.perform(MockMvcRequestBuilders.post("/superAdmin/bannedOrUnBannedAdmin")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();



    }





    @Test
    @Order(6)
    @WithUserDetails("john")
    public void bannedOrUnBannedUser() throws Exception {
        BannedOrUnBannedRequestDTO b =new BannedOrUnBannedRequestDTO();
        b.setIdUtente(4);
        ObjectMapper om=new ObjectMapper();
        String json=om.writeValueAsString(b);
        mock.perform(MockMvcRequestBuilders.post("/admin/bannedOrUnBanned")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();



    }



    @Test
    @Order(7)
    @WithUserDetails("john")
    public void rifiutoRichiesta() throws Exception {
        AcceptOrRejectRequestDTO a=new AcceptOrRejectRequestDTO();
        a.setIdRichiesta(1);
        a.setStato(Stato.RIFIUTATO);
        ObjectMapper om=new ObjectMapper();
        String json=om.writeValueAsString(a);
        mock.perform(MockMvcRequestBuilders.post("/admin/modifyTheRequest")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();



    }




    @Test
    @Order(8)
    public void registrazioneCliente() throws Exception {
        RegistrationUserRequestDTO r=new RegistrationUserRequestDTO();
        r.setNome("Giuseppe");
        r.setCognome("Alastra");
        r.setEmail("giu03@gmail.com");
        r.setUsername("peppe");
        r.setPassword("123");
        r.setPasswordRipetuta("123");
        r.setRuolo(Ruolo.CLIENTE);
        ObjectMapper om=new ObjectMapper();
        String json=om.writeValueAsString(r);
        mock.perform(MockMvcRequestBuilders.post("/all/registration")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();

    }

    @Test
    @Order(9)
    @WithUserDetails("peppe")
    public void invioRichiesta() throws Exception {

        mock.perform(MockMvcRequestBuilders.get("/cliente/richiesta"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();



    }







    @Test
    @Order(11)
    @WithUserDetails("john")
    public void accettoRichiesta() throws Exception {
        AcceptOrRejectRequestDTO a=new AcceptOrRejectRequestDTO();
        a.setIdRichiesta(2);
        a.setStato(Stato.ACCETTATO);
        ObjectMapper om=new ObjectMapper();
        String json=om.writeValueAsString(a);
        mock.perform(MockMvcRequestBuilders.post("/admin/modifyTheRequest")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();



    }

    @Test
    @Order(12)
    @WithUserDetails("john")
    public void vediTuttiVenditori() throws Exception
    {
        mock.perform(MockMvcRequestBuilders.get("/admin/findAllVenditori"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();



    }


    @Test
    @Order(13)
    @WithUserDetails("john")
    public void vediTuttiClienti() throws Exception
    {
        mock.perform(MockMvcRequestBuilders.get("/admin/findAllClienti"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();

    }



    @Test
    @Order(14)
    @WithUserDetails("chri")
    public void vediTuttiAdmin() throws Exception
    {
        mock.perform(MockMvcRequestBuilders.get("/superAdmin/findAllAdmin"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();

    }

    @Test
    @Order(15)
    @WithUserDetails("peppe")
    public void vediTuttiMieiProdotti() throws Exception
    {
        mock.perform(MockMvcRequestBuilders.get("/venditore/findMyOwnProduct"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();
    }

    @Test
    @Order(16)
    @WithUserDetails("john")
    public void vediTuttiFeedbackUser() throws Exception
    {
        FindThingsRequestDTO f=new FindThingsRequestDTO();
        f.setIdUtente(3);
        ObjectMapper om=new ObjectMapper();
        String json=om.writeValueAsString(f);
        mock.perform(MockMvcRequestBuilders.post("/admin/findAllFeedbackUser")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();
    }

    @Test
    @Order(17)
    @WithUserDetails("john")
    public void vediTuttiMessaggiUser() throws Exception
    {
        FindThingsRequestDTO f=new FindThingsRequestDTO();
        f.setIdUtente(4);
        ObjectMapper om=new ObjectMapper();
        String json=om.writeValueAsString(f);
        mock.perform(MockMvcRequestBuilders.post("/admin/findAllMessagesUser")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();
    }

    @Test
    @Order(18)
    @WithUserDetails("john")
    public void vediTuttiProdottiVenditaUser() throws Exception
    {
        FindThingsRequestDTO f=new FindThingsRequestDTO();
        f.setIdUtente(4);
        ObjectMapper om=new ObjectMapper();
        String json=om.writeValueAsString(f);
        mock.perform(MockMvcRequestBuilders.post("/admin/findAllUtenteProduct")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();
    }

    @Test
    @Order(19)
    @WithUserDetails("ale")
    public void vediTuttiGliAltriProdotti() throws Exception
    {

        FilterProductRequestDTO f =new FilterProductRequestDTO();
        f.setCondizione(Condizione.COMENUOVO);
        ObjectMapper om=new ObjectMapper();
        String json=om.writeValueAsString(f);
        mock.perform(MockMvcRequestBuilders.post( "/cliente/findTheOtherProductToCondition")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();

    }


    @Test
    @Order(20)
    @WithUserDetails("ale")
    public void vediMieiFeedback() throws Exception
    {
        mock.perform(MockMvcRequestBuilders.get("/cliente/findMyOwnFeedback"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();
    }

    @Test
    @Order(21)
    @WithUserDetails("ale")
    public void vediMieiMessaggi() throws Exception
    {
        mock.perform(MockMvcRequestBuilders.get("/cliente/findMyOwnMessage"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();
    }



    @Test
    @Order(22)
    public void login() throws Exception {
        LoginRequestDTO l=new LoginRequestDTO();
        l.setUsername("ale");
        l.setPassword("Alessia-2003");
        ObjectMapper om=new ObjectMapper();
        String json=om.writeValueAsString(l);
        mock.perform(MockMvcRequestBuilders.post("/all/login")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .content(json)
                ).andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andReturn();
    }

    @Test
    @Order(23)
    @WithUserDetails("john")
    public void logout() throws Exception {

        mock.perform(MockMvcRequestBuilders.get("/all/logout"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();
    }

    @Test
    @Order(24)
    @WithUserDetails("peppe")
    public void registraProdotto() throws Exception
    {
        InsertOrModifyProductRequestDTO i=new InsertOrModifyProductRequestDTO();
        i.setNome("Lamborghini");
        i.setDescrizione("Bellissima Macchina");
        i.setPrezzo(750.40);
        i.setQuantita(10);
        i.setCondizione(Condizione.NUOVO);
        ObjectMapper om=new ObjectMapper();
        String json=om.writeValueAsString(i);
        mock.perform(MockMvcRequestBuilders.post("/venditore/registraProdotto")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();
    }


    @Test
    @Order(25)
    @WithUserDetails("peppe")
    public void modificaProdotto() throws Exception
    {
        InsertOrModifyProductRequestDTO i=new InsertOrModifyProductRequestDTO();
        i.setNome("Lamborghini");
        i.setDescrizione("Bellissima Macchina Italiana");
        i.setQuantita(9);
        i.setPrezzo(750.30);
        i.setCondizione(Condizione.COMENUOVO);
        ObjectMapper om=new ObjectMapper();
        String json=om.writeValueAsString(i);
        mock.perform(MockMvcRequestBuilders.post("/venditore/modificaProdotto")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();
    }

    @Test
    @Order(26)
    @WithUserDetails("ale")
    public void aggiungiAlCarrello() throws Exception
    {
        AddProductToCartRequestDTO ad=new AddProductToCartRequestDTO();
        ad.setIdProdotto(1);
        ad.setQuantita(3);
        ObjectMapper om=new ObjectMapper();
        String json=om.writeValueAsString(ad);
        mock.perform(MockMvcRequestBuilders.post("/cliente/aggiungiOggettoCarrello")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();
    }

    @Test
    @Order(27)
    @WithUserDetails("ale")
    public void aggiungiAlCarrelloAgain() throws Exception
    {
        AddProductToCartRequestDTO ad=new AddProductToCartRequestDTO();
        ad.setIdProdotto(1);
        ad.setQuantita(3);
        ObjectMapper om=new ObjectMapper();
        String json=om.writeValueAsString(ad);
        mock.perform(MockMvcRequestBuilders.post("/cliente/aggiungiOggettoCarrello")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();
    }

    @Test
    @Order(28)
    @WithUserDetails("ale")
    public void sottraiDalCancello() throws Exception
    {
        SubtractQuantityRequestDTO sub=new SubtractQuantityRequestDTO();
        sub.setIdOggettocarrello(1);
        sub.setQuantita(2);
        ObjectMapper om=new ObjectMapper();
        String json=om.writeValueAsString(sub);
        mock.perform(MockMvcRequestBuilders.post("/cliente/sottraiQuantita")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();
    }

    @Test
    @Order(29)
    @WithUserDetails("ale")
    public void cancellaDalCarrello() throws Exception
    {
        DeleteObjectFromCartRequestDTO sub=new DeleteObjectFromCartRequestDTO();
        sub.setIdOggettocarrello(1);
        ObjectMapper om=new ObjectMapper();
        String json=om.writeValueAsString(sub);
        mock.perform(MockMvcRequestBuilders.post("/cliente/eliminaOggettoCarrello")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();
    }

    @Test
    @Order(30)
    @WithUserDetails("ale")
    public void mandaMessaggio() throws Exception
    {
        SendMessageRequestDTO mes=new SendMessageRequestDTO();
        mes.setIdUtente(6);
        mes.setTestoMessaggio("Ciao bella laborghini, potrei avere uno sconto? ");
        ObjectMapper om=new ObjectMapper();
        String json=om.writeValueAsString(mes);
        mock.perform(MockMvcRequestBuilders.post("/cliente/mandaMessaggio")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();
    }


    //rifatto il controllo per testare la conferma del carrello e il feedback
    @Test
    @Order(31)
    @WithUserDetails("ale")
    public void aggiungiAlCarrelloAgain2() throws Exception
    {
        AddProductToCartRequestDTO ad=new AddProductToCartRequestDTO();
        ad.setIdProdotto(1);
        ad.setQuantita(3);
        ObjectMapper om=new ObjectMapper();
        String json=om.writeValueAsString(ad);
        mock.perform(MockMvcRequestBuilders.post("/cliente/aggiungiOggettoCarrello")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();
    }

    @Test
    @Order(32)
    @WithUserDetails("ale")
    public void confermaCarrello() throws Exception {

        mock.perform(MockMvcRequestBuilders.get("/cliente/confermaCarrello"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();
    }

    @Test
    @Order(33)
    @WithUserDetails("ale")
    public void mandaFeedback() throws Exception
    {
        AddFeedbackRequestDTO ad=new AddFeedbackRequestDTO();
        ad.setIdProdotto(1);
        ad.setDescrizione("Bel prodotto ");
        ad.setValutazione(3);
        ObjectMapper om=new ObjectMapper();
        String json=om.writeValueAsString(ad);
        mock.perform(MockMvcRequestBuilders.post("/cliente/mandaFeedback")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();
    }

    @Test
    @Order(34)
    @WithUserDetails("ale")
    public void modificaFeedback() throws Exception
    {
        ModifyFeedbackRequestDTO md=new ModifyFeedbackRequestDTO();
        md.setIdFeedback(1);
        md.setDescrizione("Bella macchina ");
        md.setValutazione(3);
        ObjectMapper om=new ObjectMapper();
        String json=om.writeValueAsString(md);
        mock.perform(MockMvcRequestBuilders.post("/cliente/modificaFeedback")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();
    }


    //testing su eccezioni


    @Test
    @Order(35)
    @WithUserDetails("ale")
    public void aggiungiDueFeedabackAlloStessoProdotto() throws Exception {
        AddFeedbackRequestDTO fe = new AddFeedbackRequestDTO();
        fe.setIdProdotto(1);

        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(fe);
        mock.perform(MockMvcRequestBuilders.post("/cliente/mandaFeedback")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
    }

    @Test
    @Order(36)
    @WithUserDetails("ale")
    public void aggiungiDueFeedabackAdUnProdottoInesistente() throws Exception {
        AddFeedbackRequestDTO fe = new AddFeedbackRequestDTO();
        fe.setIdProdotto(1);
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(fe);
        mock.perform(MockMvcRequestBuilders.post("/cliente/mandaFeedback")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @Order(37)
    @WithUserDetails("ale")
    public void mandaMessaggioATeStesso() throws Exception {
        AddFeedbackRequestDTO fe = new AddFeedbackRequestDTO();
        fe.setIdProdotto(1);
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(fe);
        mock.perform(MockMvcRequestBuilders.post("/cliente/mandaMessaggio")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


    @Test
    @Order(38)
    public void loginErrato() throws Exception
    {
        LoginRequestDTO l=new LoginRequestDTO();
        l.setUsername("john");
        l.setPassword("asdsasad");
        ObjectMapper om=new ObjectMapper();
        String json=om.writeValueAsString(l);
        mock.perform(MockMvcRequestBuilders.post("/all/login")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                ).andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();
    }

    @Test
    @Order(39)
    @WithUserDetails("peppe")
    public void prodottoDuplicato() throws Exception
    {


        InsertOrModifyProductRequestDTO i=new InsertOrModifyProductRequestDTO();
        i.setNome("Lamborghini");
        i.setDescrizione("Bellissima Macchina");
        i.setPrezzo(750.40);
        i.setQuantita(10);
        i.setCondizione(Condizione.NUOVO);
        ObjectMapper om=new ObjectMapper();
        String json=om.writeValueAsString(i);
        mock.perform(MockMvcRequestBuilders.post("/venditore/registraProdotto")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                ).andExpect(MockMvcResultMatchers.status().isSwitchingProtocols())
                .andReturn();

    }

    @Test
    @Order(40)
    @WithUserDetails("peppe")
    public void prodottoConCampiNull() throws Exception
    {


        InsertOrModifyProductRequestDTO i=new InsertOrModifyProductRequestDTO();
        i.setNome(null);
        i.setDescrizione(null);
        i.setPrezzo(0);
        i.setQuantita(0);
        i.setCondizione(null);
        ObjectMapper om=new ObjectMapper();
        String json=om.writeValueAsString(i);
        mock.perform(MockMvcRequestBuilders.post("/venditore/registraProdotto")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                ).andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();

    }


    @Test
    @Order(10)
    @WithUserDetails("peppe")
    public void invioRichiestaNuovamente() throws Exception {

        mock.perform(MockMvcRequestBuilders.get("/cliente/richiesta"))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized())
                .andReturn();



    }

    @Test
    @Order(41)
    @WithUserDetails("ale")
    public void mandaMessaggioSenzaCampi() throws Exception
    {
        SendMessageRequestDTO mes=new SendMessageRequestDTO();
        mes.setIdUtente(0);
        mes.setTestoMessaggio(null);
        ObjectMapper om=new ObjectMapper();
        String json=om.writeValueAsString(mes);
        mock.perform(MockMvcRequestBuilders.post("/cliente/mandaMessaggio")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                ).andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
    }


    @Test
    @Order(42)
    @WithUserDetails("peppe")
    public void modificaProdottoPrezzoEQuantita0() throws Exception
    {
        InsertOrModifyProductRequestDTO i=new InsertOrModifyProductRequestDTO();
        i.setNome("Lamborghini");
        i.setDescrizione("Bella");
        i.setQuantita(0);
        i.setPrezzo(0);
        i.setCondizione(Condizione.COMENUOVO);
        ObjectMapper om=new ObjectMapper();
        String json=om.writeValueAsString(i);
        mock.perform(MockMvcRequestBuilders.post("/venditore/modificaProdotto")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                ).andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
    }





}

