package com.github.redfox197.demo.database.service;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.redfox197.demo.database.entity.Utente;
import com.github.redfox197.demo.database.repository.UtenteRepo;

import jakarta.transaction.Transactional;

@Service
public class UtenteService {
    @Autowired
    private UtenteRepo utenteRepo;

    public List<Utente> findAll() {
        return utenteRepo.findAll();
    }

    public void save(Utente utente) {
        utenteRepo.save(utente);
    }

    public void delete(Utente utente) {
        utenteRepo.delete(utente);
    }

    @Transactional
    public Utente findById(long id) {
        Utente utente = utenteRepo.findById(id).orElse(null);

        if (utente != null)
            Hibernate.initialize(utente.getSubReddits());

        return utente;
    }

    public List<Utente> findByNomeStartingWithA() {
        return utenteRepo.findByNomeStartingWith("a");
    }

    public List<Utente> findByCreditoGreatherThan10() {
        return utenteRepo.findByCreditoGreaterThan(10 * 100);
    }

    public List<Utente> findByNomeNullOrCognomeNull() {
        return utenteRepo.findByNomeNullOrCognomeNull();
    }

    public List<Utente> findyByCreditoBetween0And10() {
        return utenteRepo.findByCreditoBetween(0, 10 * 100);
    }
}
