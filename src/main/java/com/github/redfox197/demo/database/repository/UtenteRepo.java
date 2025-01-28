package com.github.redfox197.demo.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.redfox197.demo.database.entity.Utente;
import java.util.List;


@Repository
public interface UtenteRepo extends JpaRepository<Utente, Long>{
    List<Utente> findByNomeStartingWith(String start);
    List<Utente> findByCreditoGreaterThan(int great);
    List<Utente> findByNomeNullOrCognomeNull();
    List<Utente> findByCreditoBetween(int start, int end);
}
