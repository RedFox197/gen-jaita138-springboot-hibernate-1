package com.github.redfox197.demo.database.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.redfox197.demo.database.entity.Utente;
import com.github.redfox197.demo.database.repository.UtenteRepo;

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

    public Optional<Utente> findById(long id) {
        return utenteRepo.findById(id);
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
