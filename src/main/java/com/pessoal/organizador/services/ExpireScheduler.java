package com.pessoal.organizador.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pessoal.organizador.repositorys.NotasRepository;

@Service
public class ExpireScheduler {

    @Autowired
    private NotasRepository repository;

    @Scheduled(fixedRate = 60000) // roda a cada 1 minuto
    @Transactional
    public void verificarExpiradas() {
        repository.marcarExpiradas(LocalDateTime.now());
    }
}