package com.bytebazaar.bytebazaar;

import com.bytebazaar.bytebazaar.dto.request.LoginRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
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
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ByteBazaarApplicationTests {

    @Autowired
    private MockMvc mock;

    @Test
    @Order(2)
    void provaLoginConCredenzialiErrate() throws Exception {
        LoginRequest l=new LoginRequest();
        l.setUsername("a.grillo@elis.org");
        l.setPassword("P4ssw0rd!1");
        ObjectMapper om=new ObjectMapper();
        String json=om.writeValueAsString(l);
        mock.perform(MockMvcRequestBuilders.post("/all/login")
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(json)
                ).andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andReturn();
    }

    @Test
    @Order(1)
    @WithUserDetails("usernameUtente")
    void provaGetCarrello() throws Exception {
        mock.perform(MockMvcRequestBuilders.post("/cliente/getCarrello")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idCarrello").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$.utente.username").exists())
                .andReturn();
    }

}
