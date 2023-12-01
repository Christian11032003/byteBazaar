package com.bytebazaar.bytebazaar.controller;

import com.bytebazaar.bytebazaar.service.definition.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProdottoController
{
    @Autowired
    UtenteService serviceUtente;
}
