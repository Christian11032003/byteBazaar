package com.bytebazaar.bytebazaar;

import com.bytebazaar.bytebazaar.dto.request.richiesta.AcceptOrRejectRequestDTO;
import com.bytebazaar.bytebazaar.dto.request.utente.FindThingsRequestDTO;
import com.bytebazaar.bytebazaar.dto.request.utente.LoginRequestDTO;
import com.bytebazaar.bytebazaar.dto.request.utente.RegistrationUserRequestDTO;
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
    @Order(4)
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
    @Order(5)
    @WithUserDetails("peppe")
    public void invioRichiesta() throws Exception {

        mock.perform(MockMvcRequestBuilders.get("/cliente/richiesta"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();



    }


    @Test
    @Order(6)
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
    @Order(7)
    @WithUserDetails("john")
    public void vediTuttiVenditori() throws Exception
    {
        mock.perform(MockMvcRequestBuilders.get("/admin/findAllVenditori"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();



    }


    @Test
    @Order(8)
    @WithUserDetails("john")
    public void vediTuttiClienti() throws Exception
    {
        mock.perform(MockMvcRequestBuilders.get("/admin/findAllClienti"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();

    }



    @Test
    @Order(9)
    @WithUserDetails("chri")
    public void vediTuttiAdmin() throws Exception
    {
        mock.perform(MockMvcRequestBuilders.get("/superAdmin/findAllAdmin"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();

    }

    @Test
    @Order(10)
    @WithUserDetails("peppe")
    public void vediTuttiMieiProdotti() throws Exception
    {
        mock.perform(MockMvcRequestBuilders.get("/venditore/findMyOwnProduct"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();
    }

    @Test
    @Order(11)
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
    @Order(11)
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
    @Order(12)
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
    @Order(13)
    @WithUserDetails("ale")
    public void vediTuttiGliAltriProdotti() throws Exception
    {

        mock.perform(MockMvcRequestBuilders.get("/cliente/findTheOtherProduct"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();

    }


    @Test
    @Order(14)
    @WithUserDetails("ale")
    public void vediMieiFeedback() throws Exception
    {
        mock.perform(MockMvcRequestBuilders.get("/cliente/findMyOwnFeedback"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();
    }

    @Test
    @Order(15)
    @WithUserDetails("ale")
    public void vediMieiMessaggi() throws Exception
    {
        mock.perform(MockMvcRequestBuilders.get("/cliente/findMyOwnMessage"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();
    }



    @Test
    @Order(16)
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
    @Order(17)
    @WithUserDetails("john")
    public void logout() throws Exception {

        mock.perform(MockMvcRequestBuilders.get("/all/logout"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();
    }





























}

