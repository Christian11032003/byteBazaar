package com.bytebazaar.bytebazaar.service.definition;

import com.bytebazaar.bytebazaar.dto.request.BannedOrUnBannedAdminRequest;
import com.bytebazaar.bytebazaar.dto.request.LoginRequest;
import com.bytebazaar.bytebazaar.dto.request.RegistrationUtenteRequest;
import com.bytebazaar.bytebazaar.exception.messaggiException.BadRequestException;
import com.bytebazaar.bytebazaar.exception.messaggiException.NotFoundException;
import com.bytebazaar.bytebazaar.exception.messaggiException.UnAuthorizedException;
import com.bytebazaar.bytebazaar.model.Prodotto;
import com.bytebazaar.bytebazaar.model.Utente;

import java.util.List;


public interface UtenteService
{
    //funzionalità di dell'admin
    public boolean bannedOrUnBannedAdminRequest(BannedOrUnBannedAdminRequest request) throws UnAuthorizedException, NotFoundException, BadRequestException;
    public List<Utente> findAllClientiVenditori(LoginRequest request) throws UnAuthorizedException,BadRequestException;
    public List<Utente> findAllVenditori(LoginRequest request) throws UnAuthorizedException,BadRequestException;
    public List<Utente> findAllClienti(LoginRequest request) throws UnAuthorizedException,BadRequestException;

    //funzionalità del venditore
    public List<Prodotto> findAllHisProducts(LoginRequest request) throws UnAuthorizedException,BadRequestException;

    //funzionalità del cliente
    public List<Prodotto> findAllOtherProducts(LoginRequest request) throws UnAuthorizedException,BadRequestException;

    //funzionalità


    //funzionalità di tutti
    public boolean registrationUtente(RegistrationUtenteRequest request);
    public Utente login(LoginRequest request) throws NotFoundException;

}
