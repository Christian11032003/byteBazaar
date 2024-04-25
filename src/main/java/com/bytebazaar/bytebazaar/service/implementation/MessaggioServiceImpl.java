package com.bytebazaar.bytebazaar.service.implementation;

import com.bytebazaar.bytebazaar.model.Messaggio;
import com.bytebazaar.bytebazaar.repository.MessaggioRepository;
import com.bytebazaar.bytebazaar.service.definition.MessaggioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessaggioServiceImpl implements MessaggioService
{

    @Autowired
    MessaggioRepository messaggioRepo;


    @Override
    public void salva(Messaggio m) {
        messaggioRepo.save(m);
    }
}