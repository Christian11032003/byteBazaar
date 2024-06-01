package com.bytebazaar.bytebazaar.service.implementation;

import com.bytebazaar.bytebazaar.model.Messaggio;
import com.bytebazaar.bytebazaar.repository.MessaggioRepository;
import com.bytebazaar.bytebazaar.service.definition.MessaggioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessaggioServiceImpl implements MessaggioService
{


    private final MessaggioRepository messaggioRepo;

    @Override
    public void salva(Messaggio m) {
        messaggioRepo.save(m);
    }
}